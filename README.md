# Spring API

API Endpoints

Post

```json
http://localhost:8080/medicos
```

Body

```json
{
"nome": "Pedro Delesporte",
"email": "pedro.delsport@med.com",
"crm": "485713",
"especialidade": "cardiologia",
"telefone": "11999999999",
"endereco": {
    "logradouro": "rua x",
    "bairro": "Everett",
    "cep": "65421231",
    "cidade": "Massachussets",
    "uf": "MA"
    }
}
```

Post

```json
http://localhost:8080/pacientes
```

Body

```json
{
"nome": "Gabriel Talles",
"email": "gabriel@gmeil.com",
"telefone": "10999999999",
"cpf": "12345678900",
"endereco": {
    "logradouro": "rua y",
    "bairro": "Ceilândia",
    "cep": "32000000",
    "cidade": "Brasília",
    "uf": "DF"
    }
}
```

Get

```json
http://localhost:8080/medicos?size=10&page=0&sort=nome,desc
```
