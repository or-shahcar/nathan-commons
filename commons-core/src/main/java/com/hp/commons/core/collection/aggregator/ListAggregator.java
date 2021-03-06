package com.hp.commons.core.collection.aggregator;

import com.hp.commons.core.handler.Handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: grunzwei
 * Date: 8/13/12
 * Time: 11:15 AM
 * To change this template use File | Settings | File Templates.
 *
 * an implementation of aggregator that produces a list of elements based on the aggregated collection.
 * the produced elements are created by a applying a {@link Handler} object on the currently aggregated object.
 * the handler is given during the aggregator initialization.
 *
 * @param <R> the type of the returned list aggregation result
 * @param <T> the type of the collection being aggregated
 */
public class ListAggregator<R,T> implements Aggregator<List<R>, T> {

    /**
     * the handler to apply on each element of the collection being aggregated
     */
    private Handler<R, T> handler;

    /**
     *
     * @param handler the handler to apply on each element of the collection being aggregated
     */
    public ListAggregator(Handler<R, T> handler) {
        this.handler = handler;
    }

    /**
     * the aggregation result
     */
    List<R> ret;

    @Override
    public void init(Collection<T> elements) {
        ret = new ArrayList<R>(elements.size());
    }

    @Override
    public void aggregate(T element) {
        ret.add(handler.apply(element));
    }

    /**
     *
     * @return a list whose elements are generated by applying a function on each of the aggregated
     * collection's elements
     */
    @Override
    public List<R> finish() {
        return ret;
    }
}
