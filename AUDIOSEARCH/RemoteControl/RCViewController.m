//
//  RCViewController.m
//  RemoteControl
//
//  Created by Moshe Berman on 10/1/13.
//  Copyright (c) 2013 Moshe Berman. All rights reserved.
//

#import <AVFoundation/AVFoundation.h>

#import "RCViewController.h"

#import "Notifications.h"

#import "QFVoiceParseCenter.h"

#import "RCApplication.h"

extern NSString *remoteControlPlayButtonTapped;
extern NSString *remoteControlPauseButtonTapped;
extern NSString *remoteControlTogglePlayPauseButtonTapped;
extern NSString *remoteControlStopButtonTapped;
extern NSString *remoteControlForwardButtonTapped;
extern NSString *remoteControlBackwardButtonTapped;
extern NSString *remoteControlOtherButtonTapped;

@interface RCViewController ()
{
    BOOL isplaying;
}
@property (strong, nonatomic) IBOutlet UITextView *log;

@property (strong, nonatomic) NSString *initialText;

@property (strong, nonatomic) AVPlayer *player;
@end

@implementation RCViewController

- (id)init
{
    self = [super init];
    if (self) {
        _initialText = NSLocalizedString(@"The log is empty.", @"A string that describes an empty log.");
    }
    return self;
}
- (void)viewDidLoad
{
    [super viewDidLoad];
    
	// Do any additional setup after loading the view, typically from a nib.
    isplaying = YES;
    
    if (!self.log.text.length) {
        self.log.text = self.initialText;
    }
    
    /*
     *  Listen for the appropriate notifications.
     */
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(handleNotification:) name:remoteControlPlayButtonTapped object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(handleNotification:) name:remoteControlPauseButtonTapped object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(handleNotification:) name:remoteControlTogglePlayPauseButtonTapped object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(handleNotification:) name:remoteControlStopButtonTapped object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(handleNotification:) name:remoteControlForwardButtonTapped object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(handleNotification:) name:remoteControlBackwardButtonTapped object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(handleNotification:) name:remoteControlOtherButtonTapped object:nil];
}

- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
    
    /* 
     *  Without some sort of 
     *  audio player, the remote
     *  remains unavailable to this
     *  app, and the previous app will 
     *  maintain control over it.
     */
    
    _player = [[AVPlayer alloc] initWithURL:[NSURL URLWithString:@"http://www.cityphotos.cn/ezine/44/2356b352-1269-4f88-a5ac-e156d8adef88.mp3"]];
    
    /*  Kicking off playback takes over
     *  the software based remote control
     *  interface in the lock screen and 
     *  in Control Center.
     */
    
    [_player play];
    isplaying = YES;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


#pragma mark - Button Action

- (IBAction)buttonClick:(id)sender
{
     __block UIButton *btn = (UIButton *)sender;

    [btn setTitle:@"疯狂识别中...." forState:UIControlStateNormal];
    [btn setEnabled:NO];
    //语音解析
    [QFVoiceParseCenter startVoiceParseCenterWithFilePath:nil Complete:^(NSDictionary *results) {
        NSLog(@"开始播放 XX %@",results);
        
        [[RCApplication sharedApplication] beginReceivingRemoteControlEvents];
        [[RCApplication sharedApplication] becomeFirstResponder];
        //                [MPNowPlayingInfoCenter ]
        _player = [[AVPlayer alloc] initWithURL:[NSURL URLWithString:results[@"parseresult"]]];
        [[self player] play];
        isplaying = YES;

      [btn setTitle:@"识别歌曲" forState:UIControlStateNormal];
      [btn setEnabled:YES];
    }];
}



#pragma mark - Remote Handling

/*  This method logs out when a 
 *  remote control button is pressed.
 *
 *  In some cases, it will also manipulate the stream.
 */

- (void)handleNotification:(NSNotification *)notification
{
    if ([notification.name isEqualToString:remoteControlPlayButtonTapped] || [notification.name isEqualToString:remoteControlPauseButtonTapped]||[notification.name isEqualToString:remoteControlTogglePlayPauseButtonTapped]) {
        [self updateLogWithMessage:NSLocalizedString(@"Play remote event recieved.", @"A log event for play events.")];
        
        if(!isplaying)
        {
            [[self player] play];
            isplaying = YES;
        }
        else
        {
            [[self player] pause];
            isplaying = NO;
            
        }
    }
    else if ([notification.name isEqualToString:remoteControlStopButtonTapped]) {
        [self updateLogWithMessage:NSLocalizedString(@"Stop remote event recieved.", @"A log event for stop events.")];
        [[self player] pause];
        isplaying = NO;
        
    } else if ([notification.name isEqualToString:remoteControlForwardButtonTapped]) {
        [self updateLogWithMessage:NSLocalizedString(@"Forward remote event recieved.", @"A log event for next events.")];
        
    } else if ([notification.name isEqualToString:remoteControlBackwardButtonTapped]) {
        [self updateLogWithMessage:NSLocalizedString(@"Back remote event recieved.", @"A log event for back events.")];
    }
    else {
        [self updateLogWithMessage:NSLocalizedString(@"Unknown remote event recieved.", @"A log event for unknown events.")];
    }
    
}

#pragma mark - Log Updating

- (void)updateLogWithMessage:(NSString *)message
{
    if ([self.log.text isEqualToString:self.initialText]) {
        self.log.text = [NSMutableString stringWithFormat:@"%@: %@", [NSDate date], message];
    }
    else {
        self.log.text = [NSMutableString stringWithFormat:@"%@\n%@: %@", self.log.text, [NSDate date], message];
    }
}



@end
