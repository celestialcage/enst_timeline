export async function fetchTurnData() {
  const searchUrl = "http://localhost:8080/api/system-admin/grid";

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
