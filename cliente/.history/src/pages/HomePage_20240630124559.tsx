import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import Header from '@/components/ui/header';

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh; /* altura da tela toda */
  text-align: center;
`;

const Title = styled.h1`
  font-size: 2em; /* tamanho da fonte */
  color: #333; /* cor da fonte */
`;

export function HomePage() {
  return (
    <>
      <Container>
        <Title>
          <Link to='/historico-pedido'>Historico de pedidos</Link>
        </Title>
      </Container>
    </>
  );
}
