package com.bipin.gobble;

import com.bipin.gobble.helpers.BoundaryIndex;
import com.bipin.gobble.helpers.Constants;
import com.bipin.gobble.helpers.HelperUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

  public static void main(String[] args) {
    // write your code here

    List<String> words =
        List.of("oath", "pea", "eat","hira", "rain", "oat", "kira", "nakin", "haki","hakil");
//        List.of("oat","oatt");
//        List.of("cat","dog","byte","tube","can","ant","car","tank");


    // write your code here
    List<List<Character>> gridList =
        List.of(
            List.of('o', 'a', 'a', 'n'),
            List.of('e', 't', 'a', 'e'),
            List.of('l', 'h', 'k', 'r'),
            List.of('i', 'n', 'i', 'a'));
    char[][] grid = new char[4][4];

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        grid[i][j] = gridList.get(i).get(j);
      }
    }


    List<String> output =
        new Main()
            .findValidWords(
                grid,
                words);

    System.out.println(output.size());
    output.forEach(System.out::println);


  }


  List<String> findValidWords(char[][] grid, List<String> inputWords) {
    TrieNode root= new TrieNode();
    inputWords.forEach(s->root.add(s,s));

    Set<String> returnWords = new HashSet<>();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        char c = grid[i][j];
        if (root.node.containsKey(c)) {
          dfs(grid, i, j, root, c, returnWords);
        }
      }
    }
    return new ArrayList<>(returnWords);
  }


  private void dfs(char[][] grid, int i, int j, TrieNode root, char c, Set<String> returnWords) {

    List<BoundaryIndex> boundaryIndices= HelperUtils.getDiagonalAxisPoints();
//    boundaryIndices.addAll(HelperUtils.getDiagonalAxisPoints());

    if (HelperUtils.isOutOfIndex(grid, i, j)
        || grid[i][j] == Constants.VISITED.getCode()
        || !root.node.containsKey(c )

    ) return;

    // pop current character and set that location to visited
    char currentCharacter = grid[i][j];
    grid[i][j] = Constants.VISITED.getCode();

    root = root.node.get(currentCharacter);

    if (root != null && root.value!=null) {
      returnWords.add(root.value);
    }
    for(BoundaryIndex boundaryIndex: boundaryIndices) {
          int row = i + boundaryIndex.x;
          int col = j + boundaryIndex.y;
          if (!HelperUtils.isOutOfIndex(grid, row, col)) {
            dfs(grid, row, col, root, grid[row][col], returnWords);
          }
        }

    // restore character back to the grid
    grid[i][j] = currentCharacter;
    return;
  }

}
