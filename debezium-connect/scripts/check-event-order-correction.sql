select * from counter_event c
where c.new_count + 1 != c.version;