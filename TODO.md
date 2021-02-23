- correlation id gecme
- tracing nasil calisiyor
- canlida deneyelim
- error alan event'ler icin outbox tablosuna yazip aradan gonderelim
- pg icin sadece gerekli yetkilerle baglansin
    - To give replication permissions to a user, define a PostgreSQL role that has at least the REPLICATION and LOGIN permissions. For example:  
        CREATE ROLE name REPLICATION LOGIN;
    - https://debezium.io/documentation/reference/1.4/connectors/postgresql.html#postgresql-permissions
- header'ta id degil event-id olsun. ayni zamanda payload'da gonderelim.
- add monitoring to the demo