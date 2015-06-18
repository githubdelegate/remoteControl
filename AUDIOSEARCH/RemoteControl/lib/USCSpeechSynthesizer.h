//
//  USCOnlineTTS.h
//  tts_online_test2
//
//  Created by yunzhisheng-zy on 14-12-4.
//  Copyright (c) 2014年 usc. All rights reserved.
//

#import <Foundation/Foundation.h>
#pragma mark -
#pragma mark protocal

typedef enum
{
    USCSynthesizeParam_Volume, // 音高 取值 0~100 50为标准音高,只能设整数
    USCSynthesizeParam_Speed, // 语速 取值 0~100 50为标准语速,只能设整数
    USCSynthesizeParam_VoiceName // 发音人 为说话人 @"xiaoli" 中文女生（默认） @"Joe" 英文男生，注意英文男生首字母大写
}USCSynthesizeParam;

// @class - 协议
// @brief - 语音合成的相关代理方法
@protocol USCSpeechSynthesizerDelegate <NSObject>
/**
 *  合成器启动
 */
- (void)synthesizerSpeechStart;
/**
 *  开始朗读回调
 */
- (void)synthesizerSpeechStartSpeaking;
/**
 *  合成取消回调,包括取消合成和取消播放
 */
- (void)synthesizerDidCanceled;
/**
 *  朗读暂停回调
 */
- (void)synthesizerSpeechDidPaused;
/**
 *  朗读恢复回调
 */
- (void)synthesizerSpeechDidResumed;
/**
 *  朗读结束回调
 */
- (void)synthesizerSpeechDidFinished;
/**
 *  TTS中间出错回调
 *
 *  @param error 错误对象
 */
- (void)synthesizerErrorOccurred:(NSError *)error;
/*
 *  返回合成的音频数据，数据是PCM格式,这个方法会多次回调
 *
 *  @return 音频数据
 */
- (void)synthesizedData:(NSData *)data;
@end

#pragma mark -
#pragma mark 类
// @class - 在线TTS类
// @brief - 在线把文字转语音播放
@interface USCSpeechSynthesizer : NSObject

@property (nonatomic,weak) id<USCSpeechSynthesizerDelegate> delegate;

/**
 *  初始化
 *
 *  @param appkey appkey
 *
 *  @return
 */
- (id)initWithAppkey:(NSString *)appkey secret:(NSString *)secret;

/**
 *  开始合成
 *
 *  @param text 要合成的文字
 */
- (void)speaking:(NSString *)text;

/**
 *  立即停止播放和合成
 */
- (void)cancelSpeaking;

/**
 *  暂停播放,合成继续
 */
- (void)pauseSpeaking;

/**
 *
 *  @brief  设置语音合成参数
 *
 *  @param property 合成参数key
 *  @param value    合成参数值
 */
- (BOOL)setProperty:(USCSynthesizeParam)property value:(NSString *)value;

/**
 *  恢复播放
 */
- (void)resumeSpeaking;

@end

