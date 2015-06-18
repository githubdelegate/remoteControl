//
//  QFMethodsDefine.h
//  QFPlayerOnBackground
//
//  Created by Logger on 15/6/12.
//  Copyright (c) 2015年 Logger. All rights reserved.
//

#ifndef QFPlayerOnBackground_QFMethodsDefine_h
#define QFPlayerOnBackground_QFMethodsDefine_h


#pragma - mark 单例
#define QFSINGLECLASS_INT(CLASS) +(CLASS*)shareInstance;

#define QFSINGLECLASS_IMP(CLASS) static CLASS *qfsingle; \
+(CLASS *)shareInstance \
{ \
static dispatch_once_t onceToken; \
dispatch_once(&onceToken, ^{ \
qfsingle = [[super allocWithZone:NULL] init]; \
}); \
return qfsingle; \
} \
+(id)allocWithZone:(struct _NSZone *)zone \
{ \
return [CLASS shareInstance]; \
} \


#endif
