nombre_producto: str = "ACEITE"
precio: float = 15.5
stock: int = 40
categoria:str = "abarrotes"

if stock > 0:
    print(f"Producto: {nombre_producto}| Precio: S/ {precio:.2f}|stock:{stock}")
else:
    print(f"Producto: {nombre_producto} agotado")

if __name__ == "__main__":
        print("Sistema Tienda Mass iniciado correctamente")