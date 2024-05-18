import { Button } from '@/components/ui/button'
import React from 'react'

export function HomePage() {
  return (
    <header className="flex items-center bg-black justify-between px-24 py-8">
      <h1 className="font-bold text-xl text-white">Comes & Bebes</h1>
      <nav>
        <div>
          {/* <Button variant="ghost">criar conta</Button> */}
          <Button>Entrar</Button>
        </div>
      </nav>
    </header>

  )
}
