import { AutoCompleteEditor } from './renderers/AutoCompleteEditor';

export function createGridOptions(autoCompleteList) {
  return {
    el: document.getElementById("grid_list"),
    data: [],
    columns: [
      {
        header: "턴",
        name: "turn",
        width: 50,
        editor: "text",
        renderer: {
          styles: {
            textAlign: "center",
          },
        },
      },
      {
        header: "년차",
        name: "game_year",
        width: 50,
        renderer: {
          styles: {
            textAlign: "center",
          },
        },
        editor: "text",
      },
      {
        header: "유형",
        name: "event_type",
        width: 50,
        renderer: {
          styles: {
            textAlign: "center",
            fontWeight: 'bold',
            color: (props) => {
              console.log(props);
              switch (props.value) {
                case 'HACO':
                  return 'gray';
                
                case 'SHUFFLE':
                  return 'red';
                
                case 'TOUR':
                  return 'green';
                
                case 'GRAND':
                  return 'blue';
              }
            }
          },
        },
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
      },
      { header: "이벤트명", name: "event_name", width: 290, editor: "text" },
      {
        header: "이벤트캐",
        name: "event_char",
        width: 70,
        renderer: {
          styles: {
            textAlign: "center",
          },
        },
        editor: {
          type: AutoCompleteEditor,
          options: {
            autoCompleteEditorOptions: {
              list: autoCompleteList,
            }
          }
        },
      },
      {
        header: "속성",
        name: "event_card_prop",
        width: 50,
        renderer: {
          styles: {
            textAlign: "center",
          },
        },
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
      },
      {
        header: "속성2",
        name: "event_card_attr",
        width: 50,
        renderer: {
          styles: {
            textAlign: "center",
          },
        },
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
      },
      {
        header: "테마(크로스)명",
        name: "tscout_name",
        width: 150,
        editor: "text",
      },
      {
        header: "테마캐",
        name: "tscout_char",
        width: 70,
        renderer: {
          styles: {
            textAlign: "center",
          },
        },
        editor: "text",
      },
      {
        header: "속성",
        name: "tscout_card_prop",
        width: 50,
        renderer: {
          styles: {
            textAlign: "center",
          },
        },
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
      },
      {
        header: "속성2",
        name: "tscout_card_attr",
        width: 50,
        renderer: {
          styles: {
            textAlign: "center",
          },
        },
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
      },
      {
        header: "피쳐(크로스)명",
        name: "fscout_name",
        width: 150,
        editor: "text",
      },
      {
        header: "피쳐캐",
        name: "fscout_char",
        width: 70,
        renderer: {
          styles: {
            textAlign: "center",
          },
        },
        editor: "text",
      },
      {
        header: "속성",
        name: "fscout_card_prop",
        width: 50,
        renderer: {
          styles: {
            textAlign: 'center',
          },
        },
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
      },
      {
        header: "속성2",
        name: "fscout_card_attr",
        width: 50,
        renderer: {
          styles: {
            textAlign: 'center',
          },
        },
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
      },
      { header: "중뮤가챠", name: "ch_scout_char", width: 70, editor: "text" },
      {
        header: "속성",
        name: "ch_scout_card_prop",
        width: 50,
        renderer: {
          styles: {
            textAlign: 'center',
          },
        },
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
      },
      {
        header: "속성2",
        name: "ch_scout_card_attr",
        width: 50,
        renderer: {
          styles: {
            textAlign: 'center',
          },
        },
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
      },
      { header: "중뮤이벤", name: "ch_event_char", width: 70, editor: "text" },
      {
        header: "속성",
        name: "ch_event_card_prop",
        width: 50,
        renderer: {
          styles: {
            textAlign: 'center',
          },
        },
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
      },
      {
        header: "속성2",
        name: "ch_event_card_attr",
        width: 50,
        renderer: {
          styles: {
            textAlign: 'center',
          },
        },
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
      },
    ],
    columnOptions: {
      frozenCount: 2, // 3개의 컬럼을 고정하고
      frozenBorderWidth: 2, // 고정 컬럼의 경계선 너비를 2px로 한다.
    },
    rowHeaders: ["rowNum"],
    editingEvent: 'none', // 기본 더블클릭 막기
  };
}