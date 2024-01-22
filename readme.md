

## API para controle de funcionários, cursos e turmas.

Foi utilizado MySQL como BD, Flyway para versionamento de banco, Lombok, Validator para validar requests, 
Hikari para connection pooling, Spring Data JDBC para integrar o banco de dados, ModelMapper para mapear DTOs e Entities.





Imagem usada para o MySQL:
docker run --name mysql_instance -p 3306:3306 -e MYSQL_ROOT_PASSWORD=admin --cpus=1.5 -d mysql:8.0-debian
