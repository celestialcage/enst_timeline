export async function fetchTurnData() {
  const searchUrl = "http://localhost:8080/api/admin/grid";

  const data = await fetch(searchUrl, { method: 'GET' })
    .then((response) => {
      if (!response.ok) {
        return response.json();
      } else {
        console.log(response);
      }
    })
    .catch((error) => console.error(error));

  return data;
}
