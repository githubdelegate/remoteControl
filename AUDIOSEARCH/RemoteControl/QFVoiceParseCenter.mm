//
//  QFVoiceParseCenter.m
//  QFPlayerOnBackground
//
//  Created by Logger on 15/6/12.
//  Copyright (c) 2015年 Logger. All rights reserved.
//

#import "QFVoiceParseCenter.h"
#import <Foundation/Foundation.h>
#import "Configure.h"
#import "USCSpeechUnderstander.h"
#import "USCSpeechResult.h"
#import "USCSpeechSynthesizer.h"
#import <AVFoundation/AVFoundation.h>

@interface QFVoiceParseCenter ()<USCSpeechUnderstanderDelegate,USCSpeechSynthesizerDelegate>
{
    
    
}
@property (nonatomic,strong) USCSpeechUnderstander *speechUnderstander;

@property (nonatomic,strong) USCSpeechSynthesizer *speechSynthesizer;

@property (nonatomic,strong) USCSpeechResult *speechResult;

@property (nonatomic,strong) NSMutableString  *asrString;

@property (nonatomic,copy)   QFVoiceParseCenter_ParseComplete complete;

@property (assign)  BOOL isParseing;

@end

@implementation QFVoiceParseCenter
QFSINGLECLASS_IMP(QFVoiceParseCenter)

+ (BOOL)isUseingVoiceParse
{
    return [[QFVoiceParseCenter shareInstance] isParseing];
}


+(void)startVoiceParseCenterWithFilePath:(NSString *)audiofilepath Complete:(QFVoiceParseCenter_ParseComplete)complete
{
    [[QFVoiceParseCenter shareInstance] setUpDefaultSpeech];
    [QFVoiceParseCenter shareInstance].complete = [complete copy];
    
    [[QFVoiceParseCenter shareInstance] startOrStopWithPath:audiofilepath];
}

+ (void)stopVoiceParse
{
    [[QFVoiceParseCenter shareInstance] startOrStopWithPath:nil];
}

-(void)setUpDefaultSpeech
{

    // 创建对象
    USCSpeechUnderstander *speechUnderstander = [[USCSpeechUnderstander alloc]initWithContext:nil appKey:APPKEY secret:SECRET];
    self.speechUnderstander = speechUnderstander;
    self.speechUnderstander.delegate = self;

#warning 这里需要添加自己AudioSession Category
    [self.speechUnderstander setAudioSessionCategory:kAudioSessionCategory_RecordAudio];

    // 设置语义理解场景
    [self.speechUnderstander setNluScenario:SCENE_MUSIC];
    [self.speechUnderstander setPunctuation:YES];

#warning 这个需要有一个名称为beep.wav的音频文件才能播放提示音
    //开启播放提示音
    [self.speechUnderstander setPlayingBeep:YES];

#warning 合成的新接口
    // 合成对象
    USCSpeechSynthesizer *speechSynthesizer = [[USCSpeechSynthesizer alloc]initWithAppkey:APPKEY secret:SECRET];
    self.speechSynthesizer = speechSynthesizer;
    speechSynthesizer.delegate = self;

}


- (void)startOrStopWithPath:(NSString *)audiofilepath
{
    if (_isParseing) {
        [self.speechUnderstander stop];
        [self.speechSynthesizer cancelSpeaking];
        _isParseing = NO;
        
        return;
    }
    
    [self clear];
    
    
    // 开始语音理解
    if([audiofilepath length] > 0)
        [self.speechUnderstander recognizeAudioFile:audiofilepath];
    else
        [self.speechUnderstander start];
    
    _isParseing = YES;
}

- (void)clear
{
    self.asrString = [[NSMutableString alloc]initWithString:@""];

    _isParseing = NO;
    
    [self.speechSynthesizer cancelSpeaking];
}


#pragma mark -  SpeechUnderstanderDelegate

- (void)onRecognizerResult:(NSString *)result isLast:(BOOL)isLast
{
    NSLog(@"一次返回结果");
    if (result)
        [self.asrString appendString:result];
    
    if(isLast)
    {
        // 4.语音合成
        [self.speechSynthesizer speaking:self.asrString];
    }
}

- (void)onUnderstanderResult:(USCSpeechResult *)result
{
    NSLog(@"返回语义理解结果");
    
    self.speechResult = result;
    
    NSLog(@"返回的语义理解结果 = %@",result.stringResult);
}

- (void)onSpeechStart
{
    NSLog(@"开始说话");
}

- (void)onRecognizerStart
{
    NSLog(@"识别开始");
    
}

- (void)onRecordingStop:(NSData *)data
{
    NSLog(@"识别结束");
}

- (void)onRecordingStart
{
    NSLog(@"录音开始");
}

- (void)onVADTimeout
{
    NSLog(@"vad timeout");
}

- (void)onUpdateVolume:(int)volume
{
    NSLog(@"正在录音..."); 
}

- (void)onEnd:(NSError *)error
{
    _isParseing = NO;
    if(error)
    {
        NSLog(@"错误：%@",[NSString stringWithFormat:@"状态:%@",error.domain]);
        if(_complete)
        {
            _complete(@{@"errormsg":error.domain});
        }
        return;
    }
    else if (self.asrString.length == 0 )
    {
        NSLog(@"没有听到声音");
        if(_complete)
        {
            _complete(@{@"errormsg":@"没有听到声音"});
        }
    }
    else if(_complete)
    {
        //speechResult
        NSDictionary *dic = [NSJSONSerialization JSONObjectWithData:[self.speechResult.stringResult dataUsingEncoding:NSUTF8StringEncoding] options:NSJSONReadingMutableContainers error:nil];
        _complete(@{@"parseresult":[dic[@"data"][@"result"][@"musicinfo"] firstObject][@"url"]});
    }
    NSLog(@"语义理解完成😄");

#warning 如果audio session category 设置成record 的需要添加下面两行代码,才能播放
    [[AVAudioSession sharedInstance] setCategory:AVAudioSessionCategoryPlayback error:nil];
    [[AVAudioSession sharedInstance] setActive:YES error:nil];
}

#pragma mark - 合成代理回调

- (void)synthesizerSpeechStart
{
    NSLog(@"开始合成");
}

- (void)synthesizerSpeechStartSpeaking
{
    NSLog(@"开始朗读"); 
}

- (void)synthesizerDidCanceled
{
    NSLog(@"播放取消");
}

- (void)synthesizerSpeechDidPaused
{
    NSLog(@"播放暂停");
}


- (void)synthesizerSpeechDidResumed
{
    NSLog(@"播放恢复");
}

- (void)synthesizerSpeechDidFinished
{
    NSLog(@"播放完成!😝");
}

- (void)synthesizerErrorOccurred:(NSError *)error
{
    NSLog(@"出错了: %@",error.domain);
}


@end
