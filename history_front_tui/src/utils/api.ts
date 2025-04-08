export async function fetchTurnData() {
  const searchUrl = "http://localhost:8080/api/system-admin/turns";

  const data = await fetch(searchUrl, { method: 'GET' })
    .then((response) => {
      if (!response.ok) {
        throw new Error(`status: ${response.status}`);
      } else {
        return response.json();
      }
    })
    .catch((error) => console.error(error));

  return data;
}

export async function fetchIdolList() {
  const searchUrl = "http://localhost:8080/api/character/list";

  const data = await fetch(searchUrl, { method: 'GET' })
    .then((response) => {
      if (!response.ok) {
        throw new Error(`status: ${response.status}`);
      } else {
        return response.json();
      }
    })
    .catch((error) => console.error(error));

  return data;
}

export async function sendCreateTurnsRequest(list: Array<Object>) {
  const searchUrl = '';
  const data = await fetch(searchUrl, { method: 'POST' });

}

export async function sendUpdateTurnsRequest(list: Array<Object>) {
  const searchUrl = '';
  const data = await fetch(searchUrl, { method: 'PATCH' });

}

export async function sendDeleteTurnsRequest(list: Array<Object>) {
  const searchUrl = '';
  const data = await fetch(searchUrl, { method: 'DELETE' });

}