import { Link } from "react-router-dom";
import { Button } from "./ui/button";
import { AuthContext } from "@/context/AuthContext";
import { useContext } from "react";

export function Header() {
  const { isAuthenticated, setIsAuthenticated } = useContext(AuthContext);

  const handleLogout = () => {
    localStorage.removeItem("access-token");
    setIsAuthenticated(false);
    sessionStorage.clear();
  };

  return (
    <header className="flex items-center bg-black justify-between px-24 py-8">
      <Link to={"/"}>
        <h1 className="font-bold text-xl text-white">Comes & Bebes</h1>
      </Link>
      <nav>
        <div className="flex flex-row gap-4">
          {!isAuthenticated ? (
            <>
              <Link to={"/cadastro"}>
                <Button>Criar conta</Button>
              </Link>
              <Link to={"/login"}>
                <Button>Entrar</Button>
              </Link>
            </>
          ) : (
            <>
              <Link to="/historico-pedido">
                <Button>Histórico de Pedidos</Button>
              </Link>
              <Link to="/carrinho">
                <Button>Carrinho</Button>
              </Link>
              <Button onClick={handleLogout}>
                <Link to="/">Logout</Link>
              </Button>
            </>
          )}
        </div>
      </nav>
    </header>
  );
}
