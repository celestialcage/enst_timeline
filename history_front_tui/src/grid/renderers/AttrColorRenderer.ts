export class AttrColorRenderer {
    constructor(props) {
      this.el = document.createElement('div');
      this.el.style.textAlign = "center";
      this.el.style.fontWeight = "bold";
      this.render(props);
    }
  
    // 셀 값에 따라 색상을 설정하는 메서드
    render(props) {
      const { value } = props;
      const listItems = props.columnInfo.editor.options.listItems;
      let color = '';
      switch (value) {
        case "VOCAL":
          color = "#1e4e5f";
          break;
        case "DANCE":
          color = "#d32f2f";
          break;
        case "PERFORMANCE":
          color = "#fbc02d";
          break;
        default:
          color = 'black';
      }

      this.el.style.color = color; // 텍스트 색상 변경
      this.el.innerText = listItems.find(e => e.value === value)?.text ?? ''; // 셀 내용 설정
    }
  
    // 렌더링된 셀 반환
    getElement() {
      return this.el;
    }
  }