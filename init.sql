-- Crea la base de datos si no existe con postgres
DO
$$
BEGIN
    IF NOT EXISTS (SELECT datname FROM pg_catalog.pg_database WHERE datname = 'ganaderia') THEN
        CREATE DATABASE "ganaderia";
END IF;
END
$$;





