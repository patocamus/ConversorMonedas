# 💱 Conversor de Monedas en Java 17

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![License](https://img.shields.io/badge/license-MIT-green.svg)
![Status](https://img.shields.io/badge/status-working-brightgreen)

Una aplicación de consola desarrollada en Java 17 que permite convertir entre diferentes monedas en tiempo real utilizando la [API de ExchangeRate](https://www.exchangerate-api.com/).  
Ideal para aprender sobre consumo de APIs, parseo de JSON, uso de `HttpClient`, lógica de conversión y manejo de interfaces de texto 📈

---

## 🚀 Funcionalidades

- ✅ Consulta tasas de cambio en tiempo real
- ✅ Soporte para monedas: `ARS`, `BOB`, `BRL`, `CLP`, `COP`, `USD`
- ✅ Conversión entre monedas con cálculo automático
- ✅ Menú interactivo en consola
- ✅ Registro de historial de conversiones con fecha y hora ⏱️
- ✅ Código 100% modular y mantenible

---

## 📦 Tecnologías utilizadas

- Java 17 ☕
- API `HttpClient` (Java estándar)
- API pública: [ExchangeRate API](https://www.exchangerate-api.com/)
- Gson (para parsear JSON)
- `java.time.LocalDateTime` para timestamps
- Interfaz por consola (`Scanner`)

---

## 📷 Vista previa

```shell
Bienvenido/a al Conversor de Moneda 🪙
Seleccione la moneda BASE:
 - ARS
 - BOB
 - BRL
 - CLP
 - COP
 - USD
Ingrese código: USD
Seleccione la moneda DESTINO:
Ingrese código: ARS
Ingrese el monto a convertir: 100
✔️ 100.00 USD equivale a 87000.00 ARS
