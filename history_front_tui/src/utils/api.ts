export async function fetchTurnData() {
  const searchUrl = "http://localhost:8080/api/system-admin/turns";

  const data = await fetch(searchUrl, { method: "GET" })
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

  const data = await fetch(searchUrl, { method: "GET" })
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

export async function sendCreateTurnsRequest(data: Array<Object>) {
  const baseUrl = "http://localhost:8080/api/system-admin/turns";
  const url = data.length === 1 ? baseUrl : `${baseUrl}/list`;
  const sendData = data.length === 1 ? data[0] : data;
  console.log("sendData: ", sendData);
  console.log("sendDataJSON: ", JSON.stringify(sendData));

  try {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(sendData),
    });


    if (!response.ok) {
      throw new Error(`HTTP error status: ${response.status}`);
    }

    const responseData = await response.then(data => {return data.json()});
    return responseData;

  } catch (error) {
    console.error("Error during fetch: ", error);
    throw error; // 에러를 다시 던져서 호출한 곳에서 처리할 수 있게
  }

}

export async function sendUpdateTurnsRequest(data: Array<Object>) { 
  const baseUrl = "http://localhost:8080/api/system-admin/turns";
  const url = data.length === 1 ? `${baseUrl}/${data[0]}` : `${baseUrl}/list`;
  const sendData = data.length === 1 ? data[0] : data;
  console.log("sendData: ", sendData);
  console.log("sendDataJSON: ", JSON.stringify(sendData));

  try {
    const response = await fetch(url, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(sendData),
    });


    if (!response.ok) {
      throw new Error(`HTTP error status: ${response.status}`);
    }

    const responseData = await response.json();
    return responseData;

  } catch (error) {
    console.error("Error during fetch: ", error);
    throw error; // 에러를 다시 던져서 호출한 곳에서 처리할 수 있게
  }

}

export async function sendDeleteTurnsRequest(data: Array<Object>) {
  const deleteUrl = "";
  const res = await fetch(deleteUrl, { method: "DELETE" });
}
