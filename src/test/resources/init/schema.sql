drop alias if exists gen_random_uuid;
CREATE ALIAS gen_random_uuid as '
    import java.util.UUID;
    @CODE
    java.util.UUID getRandomUuid() throws Exception {
      return UUID.randomUUID();
    }
'
