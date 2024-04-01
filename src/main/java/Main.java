import consistency.*;
import initializing.layer.Index;
import initializing.layer.Initialize;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws InterruptedException, SQLException {
        /**
         * change the database to test
         * call Initialize.init()
         * and Insert.insert()
         * if table and dummy values are not inserted
         */
        //When using transaction
//        Thread1 thread1 = new Thread1();
//        Thread2 thread2 = new Thread2();
//        thread1.start();
//        thread2.start();
//        thread1.join();
//        thread2.join();

        //when not using transaction
//        Thread3 thread3 = new Thread3();
//        thread3.setName("thread3");
//        Thread4 thread4 = new Thread4();
//        thread4.setName("Thread4");
//        thread3.start();
//        thread4.start();
//        thread3.join();
//        thread4.join();

        //default transaction issue - repeatable read
//        Thread5 thread5 = new Thread5();
//        thread5.setName("thread5");
//        Thread6 thread6 = new Thread6();
//        thread6.setName("thread6");
//        thread5.start();
//        thread6.start();
//        thread5.join();
//        thread6.join();

        /**
         * change the database to practice
         * call Initialize.init() to create table
         * call Initialize.init_million() to set million records
        */
        //without index
//        Thread7 thread7 = new Thread7();
//        thread7.start();
        /**
         * Hash Join  (cost=30832.00..70438.01 rows=1000000 width=13) (actual time=128.281..433.483 rows=1000000 loops=1)
         *   Hash Cond: (d.health_care_provider_id = h.id)
         *   ->  Seq Scan on doctor d  (cost=0.00..21354.00 rows=1000000 width=17) (actual time=0.014..69.534 rows=1000000 loops=1)
         *   ->  Hash  (cost=14425.00..14425.00 rows=1000000 width=4) (actual time=127.710..127.710 rows=1000000 loops=1)
         *         Buckets: 262144  Batches: 8  Memory Usage: 6446kB
         *         ->  Seq Scan on health_care_provider h  (cost=0.00..14425.00 rows=1000000 width=4) (actual time=0.008..46.500 rows=1000000 loops=1)
         * Planning Time: 3.924 ms
         * Execution Time: 452.591 ms
         */
        //with index
//        Index.create();
//        Thread7 thread7 = new Thread7();
//        thread7.start();
        /**
         * Limit  (cost=1.33..7573.50 rows=100000 width=13) (actual time=0.010..20.552 rows=100000 loops=1)
         *   ->  Merge Join  (cost=1.33..75722.95 rows=1000000 width=13) (actual time=0.010..16.808 rows=100000 loops=1)
         *         Merge Cond: (d.health_care_provider_id = h.id)
         *         ->  Index Only Scan using doctor_idx on doctor d  (cost=0.42..34744.43 rows=1000000 width=17) (actual time=0.004..5.025 rows=100000 loops=1)
         *               Heap Fetches: 0
         *         ->  Index Only Scan using health_idx on health_care_provider h  (cost=0.42..25980.42 rows=1000000 width=4) (actual time=0.005..4.177 rows=100000 loops=1)
         *               Heap Fetches: 0
         * Planning Time: 0.507 ms
         * Execution Time: 22.500 ms
         */
    }
}
