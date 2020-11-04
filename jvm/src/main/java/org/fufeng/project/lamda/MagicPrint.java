package org.fufeng.project.lamda;

import java.util.Objects;

/**
 * MagicPrint
 *
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/2/26 15:47
 */
@FunctionalInterface
public interface MagicPrint<T,K> {

	void print(T t, K k);

	default MagicPrint<T,K> andThen(MagicPrint<? super T, ? super K> after) {
		Objects.requireNonNull(after);
		return (T t,K k) -> { print(t,k); after.print(t,k); };
	}
}
