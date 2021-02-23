wal_level (enum)

    wal_level determines how much information is written to the WAL. The default value is replica, which writes enough data to support WAL archiving and replication, including running read-only queries on a standby server. minimal removes all logging except the information required to recover from a crash or immediate shutdown. Finally, logical adds information necessary to support logical decoding. Each level includes the information logged at all lower levels. This parameter can only be set at server start.

    In minimal level, no information is logged for permanent relations for the remainder of a transaction that creates or rewrites them. This can make operations much faster (see Section 14.4.7). Operations that initiate this optimization include:
    ALTER ... SET TABLESPACE
    CLUSTER
    CREATE TABLE
    REFRESH MATERIALIZED VIEW (without CONCURRENTLY)
    REINDEX
    TRUNCATE

    But minimal WAL does not contain enough information to reconstruct the data from a base backup and the WAL logs, so replica or higher must be used to enable WAL archiving (archive_mode) and streaming replication.

    In logical level, the same information is logged as with replica, plus information needed to allow extracting logical change sets from the WAL. Using a level of logical will increase the WAL volume, particularly if many tables are configured for REPLICA IDENTITY FULL and many UPDATE and DELETE statements are executed.

    In releases prior to 9.6, this parameter also allowed the values archive and hot_standby. These are still accepted but mapped to replica.

Replications slots

    Replications slots are definitely beneficial once enabled. By default, "Replication Slots" are not enabled and have to be set  manually. Among the advantages of using Replication Slots are

        Ensures master retains enough WAL segments for all replicas to receive them
        Prevents the master from removing rows that could cause recovery conflict on the replicas
        A master can only recycle the transaction log once it has been consumed by all replicas. The advantage here is that a slave can never fall behind so much that a re-sync is needed.

    Replication slots also come with some caveats.

        An orphan replication slot can cause unbounded disk growth due to piled up WAL files from the master
        Slave nodes placed under long maintenance (such as days or weeks) and that are tied to a replication slot will have unbounded disk growth due to piled up WAL files from the master

    You can monitor this by querying pg_replication_slots to determine the slots that are not used. We'll check back on this a bit later.