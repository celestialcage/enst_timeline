import * as Hangul from 'hangul-js';

type CharOption = {
  id: number;
  name: string;
};

export class AutoCompleteEditor {
  private el: HTMLElement;
  private inputEl: HTMLInputElement;
  private suggestionEl: HTMLUListElement;
  private options: CharOption[] = [];
  private value: number | null = null; // id 저장

  constructor(props) {
    const customOptions = props.columnInfo.editor.options.autoCompleteEditorOptions;

    this.el = document.createElement('div');
    this.el.style.position = 'relative';
    this.el.style.width = '100%';
    this.el.style.height = '100%';
    this.el.style.overflow = 'hidden';
    this.el.style.boxSizing = 'border-box';


    this.inputEl = document.createElement('input');
    this.inputEl.className = 'autocomplete-input';

    this.inputEl.style.width = '100%';
    this.inputEl.style.height = '100%';
    this.inputEl.style.padding = '2px 4px';
    this.inputEl.style.boxSizing = 'border-box';

    this.value = props.value || ''; // name 대신 id 전달로 변경,,,
    // this.inputEl.value = props.value || '';
    // this.options = props.column.options?.list || [];
    this.options = props.columnInfo.editor.options.autoCompleteEditorOptions?.list || [];

    this.suggestionEl = document.createElement('ul');
    this.suggestionEl.className = 'autocomplete-suggestions';
    this.suggestionEl.style.position = 'absolute';
    this.suggestionEl.style.top = '100%';
    this.suggestionEl.style.left = '0';
    this.suggestionEl.style.width = '100%';
    this.suggestionEl.style.zIndex = '10';
    this.suggestionEl.style.backgroundColor = 'white';
    this.suggestionEl.style.border = '1px solid #ccc';
    this.suggestionEl.style.listStyle = 'none';
    this.suggestionEl.style.padding = '0';
    this.suggestionEl.style.margin = '0';

    this.el.appendChild(this.inputEl);
    this.el.appendChild(this.suggestionEl);

    this.inputEl.addEventListener('input', () => {
      const filtered = this.filterOptions(this.inputEl.value);
      this.renderSuggestions(filtered);
    });
    
    this.inputEl.addEventListener('blur', () => {
      setTimeout(() => {
        this.suggestionEl.innerHTML = '';
      }, 100);
    });
  }
  getElement() {
    return this.el;
  }

  getValue() {
    return this.value;
  }

  mounted() {
    this.inputEl.focus();
    const matched = this.options.find((opt) => opt.id === this.value);
    if (matched) {
      this.inputEl.value = matched.name;
    }
  }

  private renderSuggestions(matches: CharOption[]) {
    this.suggestionEl.innerHTML = '';
    matches.forEach((match) => {
      const li = document.createElement('li');
      li.textContent = match.name;
      li.style.padding = '4px 8px';
      li.style.cursor = 'pointer';
      li.addEventListener('mousedown', () => {
        this.inputEl.value = match.name;
        this.value = match.id;
        this.suggestionEl.innerHTML = '';
      });
      this.suggestionEl.appendChild(li);
    });
  }

  // 한글 초성 검색
  private filterOptions(input: string): CharOption[] {
    if (!input) return [];
    return this.options.filter((opt) => matchInitial(opt.name, input));
  }
  
}
  
// 초성 검색 유틸 함수
function matchInitial(word: string, keyword: string): boolean {
  const disassembled = Hangul.disassemble(word).join('');
  const keywordDis = Hangul.disassemble(keyword).join('');
  return disassembled.includes(keywordDis);
}