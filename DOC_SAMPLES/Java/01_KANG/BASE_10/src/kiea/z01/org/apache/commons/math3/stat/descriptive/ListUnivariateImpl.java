/* Copyright (c) 2008-2014, KangSeok
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the HSQL Development Group nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL HSQL DEVELOPMENT GROUP, HSQLDB.ORG,
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package kiea.z01.org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
import org.apache.commons.math3.util.DefaultTransformer;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.NumberTransformer;

/**
 * @author KangSeok
 * @date 2014. 8. 21.
 * @file_name ListUnivariateImpl.java
 *
 */
public class ListUnivariateImpl extends DescriptiveStatistics implements Serializable
{
    /** Serializable version identifier */
    private static final long serialVersionUID = -8837442489133392138L;

    /**
     * Holds a reference to a list - GENERICs are going to make
     * our lives easier here as we could only accept List<Number>
     */
    protected List<Object> list;

    /** Number Transformer maps Objects to Number for us. */
    protected NumberTransformer transformer;

    /**
     * No argument Constructor
     */
    public ListUnivariateImpl(){
        this(new ArrayList<Object>());
    }

    /**
     * Construct a ListUnivariate with a specific List.
     * @param list The list that will back this DescriptiveStatistics
     */
    public ListUnivariateImpl(List<Object> list) {
        this(list, new DefaultTransformer());
    }

    /**
     * Construct a ListUnivariate with a specific List.
     * @param list The list that will back this DescriptiveStatistics
     * @param transformer the number transformer used to convert the list items.
     */
    public ListUnivariateImpl(List<Object> list, NumberTransformer transformer) {
        super();
        this.list = list;
        this.transformer = transformer;
    }

    /** {@inheritDoc} */
    @Override
    public double[] getValues() {

        int length = list.size();

        // If the window size is not INFINITE_WINDOW AND
        // the current list is larger that the window size, we need to
        // take into account only the last n elements of the list
        // as definied by windowSize

        final int wSize = getWindowSize();
        if (wSize != DescriptiveStatistics.INFINITE_WINDOW && wSize < list.size()) {
            length = list.size() - FastMath.max(0, list.size() - wSize);
        }

        // Create an array to hold all values
        double[] copiedArray = new double[length];

        for (int i = 0; i < copiedArray.length; i++) {
            copiedArray[i] = getElement(i);
        }
        return copiedArray;
    }

    /** {@inheritDoc} */
    @Override
    public double getElement(int index) {

        double value = Double.NaN;

        int calcIndex = index;

        final int wSize = getWindowSize();
        if (wSize != DescriptiveStatistics.INFINITE_WINDOW && wSize < list.size()) {
            calcIndex = (list.size() - wSize) + index;
        }


        try {
            value = transformer.transform(list.get(calcIndex));
        } catch (MathIllegalArgumentException e) {
            e.printStackTrace();
        }

        return value;
    }

    /** {@inheritDoc} */
    @Override
    public long getN() {
        int n = 0;

        final int wSize = getWindowSize();
        if (wSize != DescriptiveStatistics.INFINITE_WINDOW) {
            if (list.size() > wSize) {
                n = wSize;
            } else {
                n = list.size();
            }
        } else {
            n = list.size();
        }
        return n;
    }

    /** {@inheritDoc} */
    @Override
    public void addValue(double v) {
        list.add(Double.valueOf(v));
    }

    /**
     * Adds an object to this list.
     * @param o Object to add to the list
     */
    public void addObject(Object o) {
        list.add(o);
    }

    /**
     * Clears all statistics.
     * <p>
     * <strong>N.B.: </strong> This method has the side effect of clearing the underlying list.
     */
    @Override
    public void clear() {
        list.clear();
    }

    /**
     * Apply the given statistic to this univariate collection.
     * @param stat the statistic to apply
     * @return the computed value of the statistic.
     */
    @Override
    public double apply(UnivariateStatistic stat) {
        double[] v = this.getValues();

        if (v != null) {
            return stat.evaluate(v, 0, v.length);
        }
        return Double.NaN;
    }

    /**
     * Access the number transformer.
     * @return the number transformer.
     */
    public NumberTransformer getTransformer() {
        return transformer;
    }

    /**
     * Modify the number transformer.
     * @param transformer the new number transformer.
     */
    public void setTransformer(NumberTransformer transformer) {
        this.transformer = transformer;
    }

    /** {@inheritDoc} */
    @Override
    public void setWindowSize(int windowSize) {
        super.setWindowSize(windowSize);
        //Discard elements from the front of the list if the windowSize is less than
        // the size of the list.
        int extra = list.size() - windowSize;
        for (int i = 0; i < extra; i++) {
            list.remove(0);
        }
    }

}
