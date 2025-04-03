import { fetchTurnData } from "../utils/api";

export const grid = new tui.Grid({
  el: document.getElementById("grid_list"),
  data: [],
  columns: [
    { header: "턴", name: "turn", width: 10 },
    {
      header: "년차",
      name: "game_year",
      width: 10,
      // renderer: {
      //   styles: {
      //     backgroundColor: 'red',
      //     borderRight: '3px solid #333',
      //   },
      //   // classNames: ['border-r-4', 'border-solid', 'border-gray-800']
      // },
    },
    { header: "유형", name: "event_type", width: 50 },
    { header: "이벤트명", name: "event_name" },
    { header: "이벤트캐", name: "event_char" },
    { header: "속성", name: "event_card_attr", width: 15 },
    { header: "이벤트카드속성2", name: "event_card_prop", hidden: true },
    { header: "배수캐(크로스)", name: "tscout_char" },
    {
      header: "속성",
      name: "tscout_card_attr",
      width: 15,
      // renderer: {
      //   classNames: ['border-r-4', 'border-solid', 'border-gray-800']
      // },
    },
    { header: "배수카드속성2", name: "tscout_card_prop", hidden: true },
    { header: "피쳐캐(크로스)", name: "fscout_char" },
    {
      header: "속성",
      name: "fscout_card_attr",
      width: 15,
      // renderer: {
      //   classNames: ['border-r-4', 'border-solid', 'border-gray-800']
      // },
    },
    { header: "피쳐카드속성2", name: "fscout_card_prop", hidden: true },
    { header: "중뮤스카우트", name: "ch_scout_char" },
    {
      header: "속성",
      name: "ch_scout_card_attr",
      width: 15,
      // renderer: {
      //   classNames: ['border-r-4', 'border-solid', 'border-gray-800']
      // },
    },
    { header: "중뮤카드속성2", name: "ch_scout_card_prop", hidden: true },
    { header: "중뮤이벤트", name: "ch_event_char" },
    { header: "속성", name: "ch_event_card_attr", width: 15 },
    { header: "중뮤이벤카드속성2", name: "ch_event_card_prop", hidden: true },
  ],
});

// export function initTurnGrid() {
//   const el = document.getElementById("grid_list");
//   if (!el) return;

//   grid = new tui.Grid({
//     el,
//     data: [],
//     columns: [
//       { header: "턴", name: "turn", width: 10 },
//       {
//         header: "년차",
//         name: "game_year",
//         width: 10,
//         renderer: {
//           styles: {
//             color: 'red',
//             borderRight: '3px',
//             // 'border-right': '3px solid #333'
//           },
//           classNames: ['border-r-4', 'border-solid', 'border-gray-800']
//         },
//       },
//       { header: "유형", name: "event_type", width: 50 },
//       { header: "이벤트명", name: "event_name" },
//       { header: "이벤트캐", name: "event_char" },
//       { header: "속성", name: "event_card_attr", width: 15 },
//       { header: "이벤트카드속성2", name: "event_card_prop", hidden: true },
//       { header: "배수캐(크로스)", name: "tscout_char" },
//       {
//         header: "속성",
//         name: "tscout_card_attr",
//         width: 15,
//         renderer: {
//           classNames: ['border-r-4', 'border-solid', 'border-gray-800']
//         },
//       },
//       { header: "배수카드속성2", name: "tscout_card_prop", hidden: true },
//       { header: "피쳐캐(크로스)", name: "fscout_char" },
//       {
//         header: "속성",
//         name: "fscout_card_attr",
//         width: 15,
//         renderer: {
//           classNames: ['border-r-4', 'border-solid', 'border-gray-800']
//         },
//       },
//       { header: "피쳐카드속성2", name: "fscout_card_prop", hidden: true },
//       { header: "중뮤스카우트", name: "ch_scout_char" },
//       {
//         header: "속성",
//         name: "ch_scout_card_attr",
//         width: 15,
//         renderer: {
//           classNames: ['border-r-4', 'border-solid', 'border-gray-800']
//         },
//       },
//       { header: "중뮤카드속성2", name: "ch_scout_card_prop", hidden: true },
//       { header: "중뮤이벤트", name: "ch_event_char" },
//       { header: "속성", name: "ch_event_card_attr", width: 15 },
//       { header: "중뮤이벤카드속성2", name: "ch_event_card_prop", hidden: true },
//     ],
//   });

//   // fetchTurnData().then(data => grid.resetData(data));
// }
