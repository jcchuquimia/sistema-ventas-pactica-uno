# sistema-ventas-pactica-uno
# Pruebas

... bash
curl -i -X POST http://localhost:7070/api/v1/productos \
-H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '{
    "nombre": "Laptop Lenovo",
    "precio": 3500.0
}'
...


... bash
curl -i -X POST http://localhost:7071/api/v1/pedidos \
-H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '{
    "idProducto": 1,
    "cantidad": 2,
    "total": 150.0
}'
...