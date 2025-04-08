export class PropColorRenderer {
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
        case "SPARKLE":
          color = "#d32f2f";
          break;
        case "BRILLIANT":
          color = "#5b8a8c";
          break;
        case "FLASH":
          color = "#fbc02d";
          break;
        case "GLITTER":
          color = "#388e3c";
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
  
export class PropColorBadgeRenderer {
  constructor(props) {
    this.el = document.createElement('div');
    this.el.style.textAlign = "center";
    this.el.style.fontWeight = "bold";
    this.el.style.padding = "5px 10px"; // 뱃지에 패딩 추가
    this.el.style.borderRadius = "12px"; // 뱃지 모서리 둥글게 처리
    this.el.style.color = "white"; // 텍스트 색상 흰색
    this.render(props);
  }

  render(props) {
    const { value } = props;
    const listItems = props.columnInfo.editor.options.listItems;
    let backgroundColor = '';
    let padding = '';
    
    // 셀 값에 따라 배경색을 지정
    switch (value) {
      case "SPARKLE":
        padding = "5px 10px";
        backgroundColor = "#d32f2f";  // 빨간색
        break;
      case "BRILLIANT":
        padding = "5px 10px";
        backgroundColor = "#1976D2";  // 더 쨍한 파랑
        
        break;
      case "FLASH":
        padding = "5px 10px";
        backgroundColor = "#fbc02d";  // 노란색
        break;
      case "GLITTER":
        padding = "5px 10px";
        backgroundColor = "#388e3c";  // 초록색
        break;
      case "HACO": // ㅋㅋ 웬 하코를 여기다...
        backgroundColor = "#5b8a8c";  // 청록색
        break;
      default:
        backgroundColor = 'gray';     // 기본 색상
    }

    this.el.style.padding = padding // 뱃지에 패딩 추가
    this.el.style.backgroundColor = backgroundColor;  // 배경색 설정
    this.el.innerText = listItems.find(e => e.value === value)?.text ?? ''; // 셀 내용 설정
  }

  // 렌더링된 셀 반환
  getElement() {
    return this.el;
  }
}