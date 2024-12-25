package cn.bugstack.domain.activity.service.rule;

/**
 * @author: ts
 * @description
 * @create: 2024/12/25 16:16
 */
public abstract class AbstractActionChain implements IActionChain{
    private IActionChain next;

    @Override
    public IActionChain appendNext(IActionChain next) {
        this.next = next;
        return next;
    }

    @Override
    public IActionChain next() {
        return next;
    }
}
