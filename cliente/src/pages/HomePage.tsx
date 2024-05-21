import { Button } from '@/components/ui/button'
import React from 'react'

export function HomePage() {
  return (
    <header className="flex items-center justify-between px-24 py-8 shadow">
      <h1 className="text-xl">Comes & Bebes</h1>
      <nav>
        <div className="flex">
          {/* <Button variant="ghost">criar conta</Button> */}
          <Button variant="link" className="text-black">criar conta</Button>
          <Button>Entrar</Button>
        </div>
      </nav>
    </header>

  )
}
