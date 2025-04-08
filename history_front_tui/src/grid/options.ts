import { TypeColorRenderer } from "./renderers/TypeColorRenderer";
import { AttrColorRenderer } from "./renderers/AttrColorRenderer";
import {
  PropColorRenderer,
  PropColorBadgeRenderer,
} from "./renderers/PropColorRenderer";

export function createGridOptions(idolList) {
  return {
    el: document.getElementById("grid_list"),
    pageOptions: {
      useClient: true,
      perPage: 24,
    },
    data: [],
    columns: [
      {
        header: "턴",
        name: "turn",
        width: 50,
        editor: "text",
        align: "center",
        // renderer: {
        //   styles: {
        //     textAlign: "center",
        //   },
        // },
        rowSpan: true,
      },
      {
        header: "년차",
        name: "game_year",
        width: 50,
        align: "center",
        // renderer: {
        //   styles: {
        //     textAlign: "center",
        //   },
        // },
        editor: "text",
        filter: {
          type: "number",
          showApplyBtn: true,
          showClearBtn: true,
        },
        rowSpan: true,
      },
      {
        header: "유형",
        name: "event_type",
        width: 50,
        renderer: {
          type: TypeColorRenderer,
        },
        align: "center",
        formatter: "listItemText",
        editor: {
          type: "select",
          options: {
            listItems: [
              { text: "하코", value: "HACO" },
              { text: "셔플", value: "SHUFFLE" },
              { text: "투어", value: "TOUR" },
              { text: "대형", value: "GRAND" },
            ],
          },
        },
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
        rowSpan: true,
      },
      {
        header: "이벤트명",
        name: "event_name",
        width: 290,
        editor: "text",
        rowSpan: true,
      },
      {
        header: "이벤트캐",
        name: "event_char",
        width: 100,
        align: "center",
        formatter: "listItemText",
        editor: {
          type: "select",
          options: {
            listItems: idolList,
          },
        },
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },
      {
        header: "색",
        name: "event_card_prop",
        width: 50,
        renderer: {
          type: PropColorBadgeRenderer,
        },
        align: "center",
        formatter: "listItemText",
        editor: {
          type: "select",
          options: {
            listItems: [
              { text: "스파", value: "SPARKLE" },
              { text: "브릴", value: "BRILLIANT" },
              { text: "플래", value: "FLASH" },
              { text: "글리", value: "GLITTER" },
            ],
          },
        },
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },
      {
        header: "속성",
        name: "event_card_attr",
        width: 50,
        renderer: {
          type: AttrColorRenderer,
        },
        align: "center",
        formatter: "listItemText",
        editor: {
          type: "select",
          options: {
            listItems: [
              { text: "보컬", value: "VOCAL" },
              { text: "댄스", value: "DANCE" },
              { text: "퍼포", value: "PERFORMANCE" },
            ],
          },
        },
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },
      {
        header: "테마(크로스)명",
        name: "tscout_name",
        width: 150,
        editor: "text",
        rowSpan: true,
      },
      {
        header: "테마캐",
        name: "tscout_char",
        width: 100,
        align: "center",
        formatter: "listItemText",
        editor: {
          type: "select",
          options: {
            listItems: idolList,
          },
        },
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },
      {
        header: "색",
        name: "tscout_card_prop",
        width: 50,
        renderer: {
          type: PropColorBadgeRenderer,
        },
        align: "center",
        formatter: "listItemText",
        editor: {
          type: "select",
          options: {
            listItems: [
              { text: "스파", value: "SPARKLE" },
              { text: "브릴", value: "BRILLIANT" },
              { text: "플래", value: "FLASH" },
              { text: "글리", value: "GLITTER" },
            ],
          },
        },
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },
      {
        header: "속성",
        name: "tscout_card_attr",
        width: 50,
        renderer: {
          type: AttrColorRenderer,
        },
        align: "center",
        formatter: "listItemText",
        editor: {
          type: "select",
          options: {
            listItems: [
              { text: "보컬", value: "VOCAL" },
              { text: "댄스", value: "DANCE" },
              { text: "퍼포", value: "PERFORMANCE" },
            ],
          },
        },
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },
      {
        header: "피쳐(크로스)명",
        name: "fscout_name",
        width: 150,
        editor: "text",
        rowSpan: true,
      },
      {
        header: "피쳐캐",
        name: "fscout_char",
        width: 100,
        align: "center",
        formatter: "listItemText",
        editor: {
          type: "select",
          options: {
            listItems: idolList,
          },
        },
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },
      {
        header: "색",
        name: "fscout_card_prop",
        width: 50,
        renderer: {
          type: PropColorBadgeRenderer,
        },
        align: "center",
        formatter: "listItemText",
        editor: {
          type: "select",
          options: {
            listItems: [
              { text: "스파", value: "SPARKLE" },
              { text: "브릴", value: "BRILLIANT" },
              { text: "플래", value: "FLASH" },
              { text: "글리", value: "GLITTER" },
            ],
          },
        },
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },
      {
        header: "속성",
        name: "fscout_card_attr",
        width: 50,
        renderer: {
          type: AttrColorRenderer,
        },
        align: "center",
        formatter: "listItemText",
        editor: {
          type: "select",
          options: {
            listItems: [
              { text: "보컬", value: "VOCAL" },
              { text: "댄스", value: "DANCE" },
              { text: "퍼포", value: "PERFORMANCE" },
            ],
          },
        },
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },
      {
        header: "중뮤가챠",
        name: "ch_scout_char",
        width: 100,
        align: "center",
        formatter: "listItemText",
        editor: {
          type: "select",
          options: {
            listItems: idolList,
          },
        },
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },
      {
        header: "색",
        name: "ch_scout_card_prop",
        width: 50,
        renderer: {
          type: PropColorBadgeRenderer,
        },
        align: "center",
        formatter: "listItemText",
        editor: {
          type: "select",
          options: {
            listItems: [
              { text: "스파", value: "SPARKLE" },
              { text: "브릴", value: "BRILLIANT" },
              { text: "플래", value: "FLASH" },
              { text: "글리", value: "GLITTER" },
            ],
          },
        },
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },
      {
        header: "속성",
        name: "ch_scout_card_attr",
        width: 50,
        renderer: {
          type: AttrColorRenderer,
        },
        align: "center",
        formatter: "listItemText",
        editor: {
          type: "select",
          options: {
            listItems: [
              { text: "보컬", value: "VOCAL" },
              { text: "댄스", value: "DANCE" },
              { text: "퍼포", value: "PERFORMANCE" },
            ],
          },
        },
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },
      {
        header: "중뮤이벤",
        name: "ch_event_char",
        width: 100,
        align: "center",
        formatter: "listItemText",
        editor: {
          type: "select",
          options: {
            listItems: idolList,
          },
        },
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },
      {
        header: "색",
        name: "ch_event_card_prop",
        width: 50,
        renderer: {
          type: PropColorBadgeRenderer,
        },
        align: "center",
        formatter: "listItemText",
        editor: {
          type: "select",
          options: {
            listItems: [
              { text: "스파", value: "SPARKLE" },
              { text: "브릴", value: "BRILLIANT" },
              { text: "플래", value: "FLASH" },
              { text: "글리", value: "GLITTER" },
            ],
          },
        },
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },
      {
        header: "속성",
        name: "ch_event_card_attr",
        width: 50,
        renderer: {
          type: AttrColorRenderer,
        },
        align: "center",
        formatter: "listItemText",
        editor: {
          type: "select",
          options: {
            listItems: [
              { text: "보컬", value: "VOCAL" },
              { text: "댄스", value: "DANCE" },
              { text: "퍼포", value: "PERFORMANCE" },
            ],
          },
        },
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },
    ],
    columnOptions: {
      frozenCount: 2, // 3개의 컬럼을 고정하고
      frozenBorderWidth: 2, // 고정 컬럼의 경계선 너비를 2px로 한다.
    },
    rowHeaders: ["checkbox", "rowNum" ],
    editingEvent: "none", // 기본 더블클릭 막기
    // showDummyRows: true,
    // selectionUnit: "row",
    moveDirectionOnEnter: "down",
  };
}
