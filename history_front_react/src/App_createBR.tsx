import { createBrowserRouter, RouterProvider } from "react-router-dom";
import "./App.css";
import Home from "./pages/Home";
import TurnList from "./components/TurnList";
import TurnCalendar from "./components/TurnCalendar";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
    children: [
      {
        index: true,
        element: <TurnList />,
      },
      {
        path: "/turns/calendar",
        element: <TurnCalendar />,
      },
    ],
  },
]);

function App2() {
  return <RouterProvider router={router} />;
}

export default App2;
