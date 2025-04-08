export class TypeColorRenderer {
    constructor(props) {
      this.el = document.createElement('div');
      // this.el.style.textAlign = "center";
      this.el.style.fontWeight = "bold";
      this.render(props);
    }
  
    // 셀 값에 따라 색상을 설정하는 메서드
    render(props) {
      const { value } = props;
      const listItems = props.columnInfo.editor.options.listItems;
      let color = '';
      switch (value) {
        case "HACO":
          color = "#5b8a8c";
          break;
        case "SHUFFLE":
          color = "#e57373";
          break;
        case "TOUR":
          color = "#81c784";
          break;
        case "GRAND":
          color = "#ba68c8";
          break;
        default:
          color = '#757575';
      }

      this.el.style.color = color; // 텍스트 색상 변경
      this.el.innerText = listItems.find(e => e.value === value)?.text ?? ''; // 셀 내용 설정
    }
  
    // 렌더링된 셀 반환
    getElement() {
      return this.el;
    }
  }