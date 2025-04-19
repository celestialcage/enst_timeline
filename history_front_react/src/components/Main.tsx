import { Outlet } from "react-router-dom";

const mainClassName = ``;

const Main = () => {
  return (
    <div className={mainClassName}>
      <Outlet />
    </div>
  );
};

export default Main;
