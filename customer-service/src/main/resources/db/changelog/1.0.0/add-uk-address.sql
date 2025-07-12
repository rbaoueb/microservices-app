-- Ajout d'une contrainte unique composite pour l'adresse
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1 FROM pg_indexes WHERE indexname = 'adresse_unique_rue_client'
  ) THEN
    CREATE UNIQUE INDEX adresse_unique_rue_client
    ON adresse (rue, code_postal, customer_id);
  END IF;
END;
$$;
