package com.leetcode2019.medium;

/*
https://leetcode.com/problems/task-scheduler/
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.



Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.


Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
 */
public class TaskScheduler {
    /*
    Greedy approach
   The key is to find out how many idles do we need.
Let's first look at how to arrange them. it's not hard to figure out that we can do a "greedy arrangement": always arrange task with most frequency first.
E.g. we have following tasks : 3 A, 2 B, 1 C. and we have n = 2. According to what we have above, we should first arrange A, and then B and C. Imagine there are "slots" and we need to arrange tasks by putting them into "slots". Then A should be put into slot 0, 3, 6 since we need to have at least n = 2 other tasks between two A. After A put into slots, it looks like this:

A ? ? A ? ? A
"?" is "empty" slots.

Now we can use the same way to arrange B and C. The finished schedule should look like this:

A B C A B # A
"#" is idle

Now we have a way to arrange tasks. But the problem only asks for number of CPU intervals, so we don't need actually arrange them. Instead we only need to get the total idles we need and the answer to problem is just number of idles + number of tasks.
Same example: 3 A, 2 B, 1 C, n = 2. After arranging A, we have:
A ? ? A ? ? A
We can see that A separated slots into (count(A) - 1) = 2 parts, each part has length n. With the fact that A is the task with most frequency, it should need more idles than any other tasks. In this case if we can get how many idles we need to arrange A, we will also get number of idles needed to arrange all tasks. Calculating this is not hard, we first get number of parts separated by A: partCount = count(A) - 1; then we can know number of empty slots: emptySlots = partCount * n; we can also get how many tasks we have to put into those slots: availableTasks = tasks.length - count(A). Now if we have emptySlots > availableTasks which means we have not enough tasks available to fill all empty slots, we must fill them with idles. Thus we have idles = max(0, emptySlots - availableTasks);
Almost done. One special case: what if there are more than one task with most frequency? OK, let's look at another example: 3 A 3 B 2 C 1 D, n = 3
Similarly we arrange A first:
A ? ? ? A ? ? ? A
Now it's time to arrange B, we find that we have to arrange B like this:
A B ? ? A B ? ? A B
we need to put every B right after each A. Let's look at this in another way, think of sequence "A B" as a special task "X", then we got:
X ? ? X ? ? X
Comparing to what we have after arranging A:
A ? ? ? A ? ? ? A
The only changes are length of each parts (from 3 to 2) and available tasks. By this we can get more general equations:
partCount = count(A) - 1
emptySlots = partCount * (n - (count of tasks with most frequency - 1))
availableTasks = tasks.length - count(A) * count of tasks with most frenquency
idles = max(0, emptySlots - availableTasks)
result = tasks.length + idles

     */
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        int maxFreq = 0;
        for (int i = 0; i < tasks.length; i++) {
            freq[tasks[i] - 'A']++;
            if (maxFreq < freq[tasks[i] - 'A']) {
                maxFreq = freq[tasks[i] - 'A'];
            }
        }
        int maxCount = 0;  // trying to find out how many tasks have the highest frequency
        for (int i = 0; i < 26; i++) {
            if (maxFreq == freq[i]) {
                maxCount++;
            }
        }

        int totalParts = maxFreq - 1;
        int totalELementsPerPart = n - (maxCount - 1);  // this is tricky to figure out
        int totalRequiredSlotsToFill = totalParts * totalELementsPerPart;
        int totalAvailableElements = tasks.length - maxFreq * maxCount;
        int totalIdles = Math.max(0, totalRequiredSlotsToFill - totalAvailableElements);
        return tasks.length + totalIdles;
    }
}
