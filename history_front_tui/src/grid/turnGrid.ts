import { fetchTurnData, sendCreateTurnsRequest, sendUpdateTurnsRequest, sendDeleteTurnsRequest } from "../utils/api";

let grid;

export function initTurnGrid(gridOptions) {
  const el = document.getElementById("grid_list");
  if (!el) return;

  tui.Grid.setLanguage("ko", {
    // set new language
    display: {
      noData: "표시할 데이터가 없어요~~",
      loadingData: "데이터 로딩 중",
      resizeHandleGuide:
        "컬럼 너비를 드래그로 조절할 수 있고, " +
        "더블 클릭으로 너비를 초기화할 수 있어요.",
    },
  });

  tui.Grid.applyTheme("default");
  grid = new tui.Grid(gridOptions);

  // grid.on('focusChange', ({ rowKey, columnName }) => {
  //   console.log("focusChange: ", rowKey, columnName);
    
  //   const columnLength = grid.getColumns().length;
  //   grid.setSelectionRange({
  //     start: [rowKey, 0],
  //     end: [rowKey, columnLength - 1]
  //   });
  // });
  
  let lastFocusedCell: { rowKey: number | null; columnName: string | null } = {
    rowKey: null,
    columnName: null,
  };

  // 클릭하면 "두 번째 클릭" 조건일 때만 편집 진입
  grid.on('click', () => {
    const focus = grid.getFocusedCell();

    if (
      focus?.rowKey === lastFocusedCell.rowKey &&
      focus?.columnName === lastFocusedCell.columnName
    ) {
      grid.startEditing(focus.rowKey, focus.columnName);
    }

    // click 내에서 바로 lastFocusedCell 갱신
    lastFocusedCell = {
      rowKey: focus?.rowKey ?? null,
      columnName: focus?.columnName ?? null,
    };
  });

  fetchTurnData().then((data) => grid.resetData(data));
}

function modifyDataForGrid(apiResponse: Array<Object>) {
  const gridData: Array<Object> = [];
  apiResponse.forEach(item => {
    const rows = item.rows;

    // 각 캐릭터 라인업을 기준으로 행을 나누기
    let maxLineups = Math.max(
      rows.filter(row => row.eventChar).length,  // eventLineups
      rows.filter(row => row.tScoutChar).length, // tScoutLineups
      rows.filter(row => row.fScoutChar).length  // fScoutLineups
    );
    
    for (let i = 0; i < maxLineups; i++) {
      let rowData: any = {};

      // 이벤트 값, 값이 없으면 첫 번째 행의 값을 복제
      rowData.eventChar = rows[i]?.eventChar || rows[0]?.eventChar || '';
      rowData.eventCardAttr = rows[i]?.eventCardAttr || rows[0]?.eventCardAttr || '';
      rowData.eventCardProp = rows[i]?.eventCardProp || rows[0]?.eventCardProp || '';

      // T스카우트 값, 값이 없으면 첫 번째 행의 값을 복제
      rowData.tScoutChar = rows[i]?.tScoutChar || rows[0]?.tScoutChar || '';
      rowData.tScoutCardAttr = rows[i]?.tScoutCardAttr || rows[0]?.tScoutCardAttr || '';
      rowData.tScoutCardProp = rows[i]?.tScoutCardProp || rows[0]?.tScoutCardProp || '';

      // F스카우트 값, 값이 없으면 첫 번째 행의 값을 복제
      rowData.fScoutChar = rows[i]?.fScoutChar || rows[0]?.fScoutChar || '';
      rowData.fScoutCardAttr = rows[i]?.fScoutCardAttr || rows[0]?.fScoutCardAttr || '';
      rowData.fScoutCardProp = rows[i]?.fScoutCardProp || rows[0]?.fScoutCardProp || '';

      // 나머지 컬럼들 복제 (인덱스 0의 값으로 복제)
      rowData.turnSeq = rows[0]?.turnSeq || '';
      rowData.gameYear = rows[0]?.gameYear || '';
      rowData.eventType = rows[0]?.eventType || '';
      rowData.eventTitle = rows[0]?.eventTitle || '';
      rowData.themeScout = rows[0]?.themeScout || '';
      rowData.featureScout = rows[0]?.featureScout || '';
      rowData.chScoutChar = rows[0]?.chScoutChar || '';
      rowData.chScoutCardAttr = rows[0]?.chScoutCardAttr || '';
      rowData.chScoutCardProp = rows[0]?.chScoutCardProp || '';
      rowData.chIrrChar = rows[0]?.chIrrChar || '';
      rowData.chIrrCardAttr = rows[0]?.chIrrCardAttr || '';
      rowData.chIrrCardProp = rows[0]?.chIrrCardProp || '';

      // 각각의 라인업 캐릭터 값에 맞는 행을 추가
      gridData.push(rowData);
    }
  });

  return gridData;
}

function modifyDataForApi(modifiedRows: any) {
  return modifiedRows.map((row: any) => ({
    turnSeq: row.turn,
    gameYear: row.game_year,
    eventType: row.event_type,
    eventTitle: row.event_name,
    eventChar: row.event_char,
    eventCardAttr: row.event_card_attr, 
    eventCardProp: row.event_card_prop,
    themeScout: row.tscout_name,
    tScoutChar: row.tscout_char,
    tScoutCardAttr: row.tscout_card_attr,
    tScoutCardProp: row.tscout_card_prop,
    featureScout: row.fscout_name,
    fScoutChar: row.fscout_char,
    fScoutCardAttr: row.fscout_card_attr,
    fScoutCardProp: row.fscout_card_prop,
    chScoutChar: row.ch_scout_char,
    chScoutCardAttr: row.ch_scout_card_attr,
    chScoutCardProp: row.ch_scout_card_prop,
    chIrrChar: row.ch_event_char,
    chIrrCardAttr: row.ch_event_card_attr,
    chIrrCardProp: row.ch_event_card_prop
  }));
}

const addBtn: HTMLButtonElement | null = document.querySelector("#add-row-btn");
addBtn.addEventListener("click", () => {
  const rowInitData = {};
  const rowInitOptions = {
    // at: index,
    focus: false, // 그냥 신규 행 만들 때 focus 주면 되지 startEditing때 줘야하는지... 의문
  };

  grid.appendRow(rowInitData, rowInitOptions);


  // ----------- 오픈하니까 rowspan 때문에 여러 행 추가하면 기본값으로 cell merge 돼서ㅠ 안함
  // // 1. 전체 데이터 받아서 마지막 row 찾기
  // const lastIndex = grid.getData().length - 1;
  // const lastRow = grid.getRowAt(lastIndex); // ✅ 여기서 rowKey도 포함된 row 객체 가져옴

  // // 2. rowKey 추출
  // const rowKey = lastRow?.rowKey;
  
  // grid.startEditing(rowKey, 'turn');
});

const saveBtn: HTMLButtonElement | null = document.querySelector("#save-btn");
saveBtn?.addEventListener("click", () => {

  const rowsToInsert = modifyDataForApi(grid.getModifiedRows().createdRows);
  const rowsToUpdate = modifyDataForApi(grid.getModifiedRows().updatedRows);

  alert("저장하시겠습니까?");

  const confirmMsg = `신규 행 
  ${rowsToInsert}
  수정 행
  ${rowsToUpdate}
  `
  if (confirm(confirmMsg)) {
    console.log("create api data: ", rowsToInsert);
    console.log("update api data: ", rowsToUpdate);
  };

  if (rowsToInsert.length > 0) {
    console.log(rowsToInsert);
    sendCreateTurnsRequest(rowsToInsert);
  }

  if (rowsToUpdate.length > 0) {
    console.log(rowsToUpdate);
    sendUpdateTurnsRequest(rowsToUpdate);
  }

});

const searchBtn: HTMLButtonElement | null = document.querySelector("#search-btn");
searchBtn?.addEventListener("click", () => {
  console.log(grid.getModifiedRows());
  const rowsToInsert = grid.getModifiedRows().createdRows;
  const rowsToUpdate = grid.getModifiedRows().updatedRows;

  alert("저장하시겠습니까?")

  const confirmMsg = `신규 행 
  ${rowsToInsert}
  수정 행
  ${rowsToUpdate}
  `
  if (confirm(confirmMsg)) {
    console.log(grid.getModifiedRows());
  };

  if (rowsToInsert.length > 0) {
    console.log(rowsToInsert);
    // sendCreateTurnsRequest(rowsToInsert);
  }

  if (rowsToUpdate.length > 0) {
    console.log(rowsToUpdate);
    // sendUpdateTurnsRequest(rowsToUpdate);
  }

});

const deleteBtn: HTMLButtonElement | null = document.querySelector("#delete-btn");
deleteBtn?.addEventListener("click", () => {
  alert("삭제하면... 정말로 삭제됩니다.");

  grid.removeCheckedRows();

  const rowsToDelete = grid.getModifiedRows().deletedRows;

  const confirmMsg = `사라지는 행은 
  ${rowsToDelete}`
  if (confirm(confirmMsg)) {
    console.log(rowsToDelete);
    // sendDeleteTurnsRequest(rowsToDelete);
  };

});

const perPageSelect: HTMLSelectElement | null = document.querySelector("#itemsPerPage");
perPageSelect?.addEventListener("change", (e) => {
  const pagination = grid.getPagination();
  const currentPage = pagination.getCurrentPage();
  console.log(currentPage);
  
  grid.setPerPage(Number(e.target.value));

});