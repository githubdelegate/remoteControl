//
//  QFVoiceParseCenter.h
//  QFPlayerOnBackground
//
//  Created by Logger on 15/6/12.
//  Copyright (c) 2015年 Logger. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "QFMethodsDefine.h"

typedef void(^QFVoiceParseCenter_ParseComplete)(NSDictionary *results);

@interface QFVoiceParseCenter : NSObject

QFSINGLECLASS_INT(QFVoiceParseCenter);

+ (BOOL)isUseingVoiceParse;

+(void)startVoiceParseCenterWithFilePath:(NSString *)audiofilepath Complete:(QFVoiceParseCenter_ParseComplete)complete;

+ (void)stopVoiceParse;

@end
