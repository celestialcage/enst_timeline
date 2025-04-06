import { fetchTurnData } from "../utils/api";

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

  tui.Grid.applyTheme("striped");
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

const addBtn: HTMLButtonElement | null = document.querySelector("#add-row-btn");
addBtn.addEventListener("click", () => {
  const rowInitData = {};
  const rowInitOptions = {
    // at: index,
    focus: false, // 그냥 신규 행 만들 때 focus 주면 되지 startEditing때 줘야하는지... 의문
  };

  grid.appendRow(rowInitData, rowInitOptions);

  // 1. 전체 데이터 받아서 마지막 row 찾기
  const lastIndex = grid.getData().length - 1;
  const lastRow = grid.getRowAt(lastIndex); // ✅ 여기서 rowKey도 포함된 row 객체 가져옴

  // 2. rowKey 추출
  const rowKey = lastRow?.rowKey;
  
  grid.startEditing(rowKey, 'turn');
});
