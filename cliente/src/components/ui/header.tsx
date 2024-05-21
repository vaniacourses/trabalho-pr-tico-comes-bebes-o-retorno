import React from 'react'
import { Button } from './button'

export default function Header() {
  return (
    <header className="flex items-center justify-between px-24 py-8 shadow">
      <h1 className="font-medium text-xl">Comes & Bebes</h1>
      <nav>
        <div className="flex">
          {/* <Button variant="ghost">criar conta</Button> */}
          <Button variant="link" className="text-bold">criar conta</Button>
          <Button>Entrar</Button>
        </div>
      </nav>
    </header>
  )
}
