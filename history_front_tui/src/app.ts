import { createGridOptions } from './grid/options';
import { initTurnGrid } from './grid/turnGrid';
import { fetchIdolList } from './utils/api';

async function main() {
  const data = await fetchIdolList();
  const list = data.map(ele => ({ text: ele.name, value: String(ele.id) }));
  // console.log(list);
  list.unshift({ text: " ", value: null });

  const options = createGridOptions(list);
  initTurnGrid(options);
}

document.addEventListener("DOMContentLoaded", () => {

  main();

  // const path = window.location.pathname;
  // console.log(path);

  // if (path.includes('/')) {
  //   import('./grid/turnGrid').then(({ initTurnGrid }) => initTurnGrid())
  // }
  
});

// csv 업로드
const form = document.getElementById('csvUploadForm');
const feedback = document.getElementById('uploadFeedback');

form?.addEventListener('submit', async (e) => {
  e.preventDefault();
  const fileInput = document.getElementById('csvFile');
  const uploadType = document.getElementById('uploadType').value;
  const file = fileInput.files[0];

  if (!file) {
    showFeedback('파일을 선택해주세요.', 'text-red-500');
    return;
  }

  try {
    /* File API text() 메서드로 파일 읽기 arrayBuffer(), stream() <- 대용량용도 있지만 csv엔 text()가 적당 */
    /* 프론트 파싱은 papaparse */
    // const text = await file.text();
    // console.log('CSV 내용:', text); // 여기서 데이터 처리 로직 추가

    /* 지금은 백엔드로 바로 FormData 보내기 */
    const formData = new FormData();
    formData.append('csvFile', file); // @RequestParam 이름도 csvFile로 하면 될듯

    // ajax - fetch api
    const response = await fetch(`http://localhost:8080/api/system-admin/upload-csv/${uploadType}`, {
      method: 'POST',
      body: formData,
    });

    const result = await response.text();
    showFeedback(result, response.ok ? 'text-green-500' : 'text-red-500');

  } catch (error) {
    showFeedback('업로드 중 오류 발생: ' + error.message, 'text-red-500');
  }
});

function showFeedback(message, colorClass) {
  feedback.textContent = message;
  feedback?.classList.remove('hidden', 'text-red-500', 'text-green-500');
  feedback?.classList.add(colorClass);
}