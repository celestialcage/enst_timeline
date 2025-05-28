import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";
import "./App.css";
import Home from "./pages/Home";
import TurnList from "./components/TurnList";
import TurnCalendar from "./components/TurnCalendar";

// const router = createBrowserRouter([
//   {
//     path: "/",
//     element: <Home />,
//     children: [
//       {
//         index: true,
//         element: <TurnList />,
//       },
//       {
//         path: "/turns/calendar",
//         element: <TurnCalendar />,
//       },
//     ],
//   },
// ]);

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/turns" element={<TurnList />} />
      </Routes>
    </Router>
  );
}

export default App;
