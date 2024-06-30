import { Role } from "@/models/Usuario";
import { useEffect, useState } from "react";
import { createContext } from "react";


interface AuthContextType {
    isAuthenticated: boolean;
    setIsAuthenticated: React.Dispatch<React.SetStateAction<boolean>>;
    userRole:Role; 
    setUserRole:React.Dispatch<React.SetStateAction<Role>>
    isLoading:boolean;
    setIsLoading:React.Dispatch<React.SetStateAction<boolean>>;
    token?:string
    setToken:React.Dispatch<React.SetStateAction<string>>
}


export const AuthContext = createContext<AuthContextType>(
    {} as AuthContextType
  )
  
  export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({
    children,
  }) => {
    const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false)
    const [isLoading, setIsLoading] = useState<boolean>(false)
    const [userRole, setUserRole] = useState<Role>(Role.USER)
    const [token, setToken] = useState<String>("")
    
    
    useEffect(() => {
      const fetchData = async () => {
        const authenticated = localStorage.getItem('acess-token') ? true : false
        setIsAuthenticated(authenticated)
        setToken(localStorage.getItem('acess-token') || "")
        setIsLoading(false)
      }
  
      fetchData()
    }, [])

    return (
      <AuthContext.Provider
        value={{
            isAuthenticated,
            setIsAuthenticated,
            userRole,
            setUserRole,
            isLoading,
            setIsLoading
        }}
      >
        {children}
      </AuthContext.Provider>
    )
  }