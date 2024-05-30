Feature: Comparar Laptops

  Scenario: Comparar Lenovo IdeaPad 5 con Lenovo E41-80
    Given abro la pagina de comparacion
    When busco "Lenovo IdeaPad 5 82LN00A3IN Laptop AMD Ryzen 7 5700U AMD Radeon 16GB 512GB SSD Windows 10" y lo anado a la comparacion
    And busco "Lenovo E41-80" y lo anado a la comparacion
    Then extraigo los detalles del resumen a un archivo Excel
