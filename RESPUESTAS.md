# PARTE 1: RESPUESTAS DE IDENTIFICACIÓN DE CODE SMELLS

## Pregunta 1.1

**Respuesta: C) Long Parameter List**

**Explicación:** El método `generateReport` tiene demasiados parámetros. Esto hace que el método sea difícil de entender y llamar. Muchos de estos parámetros podrían agruparse en objetos más pequeños y cohesivos, como `CustomerDetails` y `ReportOptions`.

## Pregunta 1.2

**Respuesta: B) Switch Statements**

**Explicación:** La cadena `if-else if` para determinar el descuento basado en un código de tipo (`order.getType()`) es un claro ejemplo del smell "Switch Statements". Si se añade un nuevo tipo de orden, es necesario modificar este bloque de código, violando el Principio de Abierto/Cerrado. Esto podría refactorizarse usando polimorfismo.

## Pregunta 1.3

**Respuesta: A) Large Class (God Class)**

**Explicación:** La clase `Invoice` asume demasiadas responsabilidades que no le corresponden (manejo de datos de cliente, producto, pago y envío). Esto viola el Principio de Responsabilidad Única, convirtiéndola en una "Clase Dios" difícil de mantener y entender.

## Pregunta 1.4

**Respuesta: B) Data Clumps**

**Explicación:** El mismo grupo de variables (`name`, `address`, `phone`) se pasa repetidamente a múltiples métodos. Estos "grumos de datos" sugieren que deberían estar encapsulados dentro de su propio objeto (por ejemplo, una clase `Employee` o `ContactInfo`), en lugar de pasarse como parámetros individuales.

## Pregunta 1.5

**Respuesta: B) Primitive Obsession**

**Explicación:** El código abusa de los tipos primitivos (enteros) para representar conceptos complejos como `status`, `type` y `level`. El uso de "números mágicos" en lugar de tipos más ricos (como Enums o clases pequeñas) hace que el código sea menos legible, menos seguro y más propenso a errores.
