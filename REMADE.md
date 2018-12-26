  ## 1. 源码探究(实例化bean（处理器在此之前已经结束了)
     AbstractApplicationContext.refresh()；
     
     // Instantiate all remaining (non-lazy-init) singletons.
     finishBeanFactoryInitialization(beanFactory);
     
     // Instantiate all remaining (non-lazy-init) singletons.
     beanFactory.preInstantiateSingletons();