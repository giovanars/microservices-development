### Este ms realiza duas funções:

1. Fica escutando a fila _fiap.order.queue_, ou seja, todo pedido que cai aqui é 'puxado' automativamente.
2. Através de um POST, envia-se o pedido que deve ser marcado como pronto. Os pedidos prontos ficam na fila _fiap.order.done.queue_.

### Exemplo de um pedido:
```
{
    "idOrder":1, 
    "descriptionOrder":"X Burgui"
}
```