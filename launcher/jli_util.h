/*
 * Copyright (c) 2005, 2012, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

#ifndef _JLI_UTIL_H
#define _JLI_UTIL_H

#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <jni.h>
#define JLDEBUG_ENV_ENTRY "_JAVA_LAUNCHER_DEBUG"

void *JLI_MemAlloc(size_t size);
void *JLI_MemRealloc(void *ptr, size_t size);
char *JLI_StringDup(const char *s1);
void  JLI_MemFree(void *ptr);
int   JLI_StrCCmp(const char *s1, const char* s2);

typedef struct {
    char *arg;
    jboolean has_wildcard;
} StdArg;

StdArg *JLI_GetStdArgs();
int     JLI_GetStdArgc();

#define JLI_StrLen(p1)          strlen((p1))
#define JLI_StrChr(p1, p2)      strchr((p1), (p2))
#define JLI_StrRChr(p1, p2)     strrchr((p1), (p2))
#define JLI_StrCmp(p1, p2)      strcmp((p1), (p2))
#define JLI_StrNCmp(p1, p2, p3) strncmp((p1), (p2), (p3))
#define JLI_StrCat(p1, p2)      strcat((p1), (p2))
#define JLI_StrCpy(p1, p2)      strcpy((p1), (p2))
#define JLI_StrNCpy(p1, p2, p3) strncpy((p1), (p2), (p3))
#define JLI_StrStr(p1, p2)      strstr((p1), (p2))
#define JLI_StrSpn(p1, p2)      strspn((p1), (p2))
#define JLI_StrCSpn(p1, p2)     strcspn((p1), (p2))
#define JLI_StrPBrk(p1, p2)     strpbrk((p1), (p2))
#define JLI_StrTok(p1, p2)      strtok((p1), (p2))

/* On Windows lseek() is in io.h rather than the location dictated by POSIX. */
#ifdef _WIN32
#include <windows.h>
#include <io.h>
#define JLI_StrCaseCmp(p1, p2)          stricmp((p1), (p2))
#define JLI_StrNCaseCmp(p1, p2, p3)     strnicmp((p1), (p2), (p3))
#define JLI_Snprintf                    _snprintf
void JLI_CmdToArgs(char *cmdline);
#else
#include <unistd.h>
#include <strings.h>
#define JLI_StrCaseCmp(p1, p2)          strcasecmp((p1), (p2))
#define JLI_StrNCaseCmp(p1, p2, p3)     strncasecmp((p1), (p2), (p3))
#define JLI_Snprintf                    snprintf
#endif /* _WIN32 */

/*
 * Make launcher spit debug output.
 */
void     JLI_TraceLauncher(const char* fmt, ...);
void     JLI_SetTraceLauncher();
jboolean JLI_IsTraceLauncher();

#endif  /* _JLI_UTIL_H */
