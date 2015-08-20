mySum :: [Integer] -> Integer
mySum [] = 0
mySum (x:xs) = x+ mySum xs

myProd :: [Integer] -> Integer
myProd [] = 1	
myProd (x:xs) = x * myProd(xs)

myTake :: Int -> [Int] -> [Int]
myTake _ [] 	= []
myTake 0 _ 		= []
myTake n (x:xs) | n < 0 = error "boom"
				| otherwise = x : myTake (n-1) xs

-- data Day = Mon | Tue | Wen | Fri | Sat | 
-- printDay :: Day -> String
-- printDat d = case d of
-- 	Mon -> "Monday"

myApply :: (Int -> Int -> Int) -> Int -> Int -> Int
myApply f x y = f x y

myMap :: (a -> b) -> [a] -> [b]
myMap _ [] = []
myMap f (x:xs) = f x : myMap f xs

myFoldr :: (Int -> Int -> Int) -> Int -> [Int] -> Int
myFoldr _ neutral [] = neutral
myFoldr f neutral (x:xs) = f x (myFoldr f neutral xs)

-- myMapFoldr :: (a -> b) -> [a] -> [b]
-- myMapFoldr f = foldr (\x ys -> f x : ys) ] [] xs



data Tree a = Empty | Node a (Tree a) (Tree a) deriving Show

-- Tree example
tr = Node 1
		(Node 2
			(Node 3 Empty Empty)
			(Node 4 Empty Empty))
		(Node 3
			(Node 6 Empty Empty)
			(Node 7 Empty Empty))
-- Similar to map
mapTree :: (a -> b)->Tree a->Tree b
mapTree _ Empty = Empty
mapTree f (Node x l r) = Node (f x) mapTree(f l) mapTree(f r)


-- TODO
-- foldTree