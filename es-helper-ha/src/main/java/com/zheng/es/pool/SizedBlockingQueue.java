package com.zheng.es.pool;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  有界阻塞队列实现
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月20日			zhenglian			    Initial.
 *
 * </pre>
 */
public class SizedBlockingQueue<E> extends AbstractQueue<E> implements BlockingQueue<E> {
    /**
     * 队列容量
     */
    private int capacity;
    /**
     * 阻塞队列实现
     */
    private BlockingQueue<E> queue;

    /**
     * 当前队列元素个数
     */
    private AtomicInteger size = new AtomicInteger(0);
    
    public SizedBlockingQueue(BlockingQueue queue, int capacity) {
        this.queue = queue;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public Iterator<E> iterator() {
        final Iterator<E> iterator = queue.iterator();
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public E next() {
                E e = iterator.next();
                size.decrementAndGet();
                return e;
            }

            @Override
            public void remove() {
                // thread pool don't use this method
                throw new UnsupportedOperationException("不支持的操作: iterator.remove()");
            }
        };
    }

    @Override
    public int size() {
        return size.get();
    }

    @Override
    public void put(E e) throws InterruptedException {
        queue.put(e);
        size.incrementAndGet();
    }
    
    

    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        boolean result = queue.offer(e, timeout, unit);
        if(result) {
            size.incrementAndGet();
        }
        return result;
    }

    @Override
    public E take() throws InterruptedException {
        E e = queue.take();
        size.decrementAndGet();
        return e;
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        E e = queue.poll(timeout, unit);
        if (null != e) {
            size.decrementAndGet();
        }
        return e;
    }

    @Override
    public int remainingCapacity() {
        return capacity - size.get();
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        int v = queue.drainTo(c);
        size.addAndGet(-v);
        return v;
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        int v = queue.drainTo(c, maxElements);
        size.addAndGet(-v);
        return v;
    }

    @Override
    public boolean offer(E e) {
        int count = size.incrementAndGet();
        if (count <= capacity) {
            boolean result = queue.offer(e);
            if (result) {
                return true;
            }
        }
        size.decrementAndGet();
        return false;
    }

    @Override
    public E poll() {
        E e = queue.poll();
        if (null != e) {
            size.decrementAndGet();
        }
        return e;
    }

    @Override
    public E peek() {
        return queue.peek();
    }

    @Override
    public Object[] toArray() {
        return queue.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) queue.toArray(a);
    }

    @Override
    public boolean contains(Object o) {
        return queue.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return queue.containsAll(c);
    }
    
}
