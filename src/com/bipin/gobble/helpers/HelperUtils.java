package com.bipin.gobble.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author bipin on 2020-04-27 12:50
 */
public class HelperUtils {
  public static boolean isOutOfIndex(char[][] grid, int i, int j){
    return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length;
  }

  public static List<BoundaryIndex> getAllBoundaryAxisPoints(){
    List<BoundaryIndex> diagonal= getDiagonalAxisPoints();
    diagonal.addAll(getHorizontalVerticalAxisPoints());
    return diagonal;
  }

  public static List<BoundaryIndex> getDiagonalAxisPoints(){
    List<BoundaryIndex> b= Arrays.asList(new BoundaryIndex(1, 1), new BoundaryIndex(1, -1),
        new BoundaryIndex(-1, -1), new BoundaryIndex(-1, 1));
    return new ArrayList<>(b);

  }

  public static List<BoundaryIndex> getHorizontalVerticalAxisPoints(){
    List<BoundaryIndex> b= Arrays.asList(new BoundaryIndex(-1,0),new BoundaryIndex(1,0),
        new BoundaryIndex(0,-1),new BoundaryIndex(0,1));
    return new ArrayList<>(b);

  }

}
