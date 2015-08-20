module Paths_new_project (
    version,
    getBinDir, getLibDir, getDataDir, getLibexecDir,
    getDataFileName, getSysconfDir
  ) where

import qualified Control.Exception as Exception
import Data.Version (Version(..))
import System.Environment (getEnv)
import Prelude

catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
catchIO = Exception.catch


version :: Version
version = Version {versionBranch = [0,1,0,0], versionTags = []}
bindir, libdir, datadir, libexecdir, sysconfdir :: FilePath

bindir     = "/home/raziel/.cabal/bin"
libdir     = "/home/raziel/.cabal/lib/x86_64-linux-ghc-7.6.3/new-project-0.1.0.0"
datadir    = "/home/raziel/.cabal/share/x86_64-linux-ghc-7.6.3/new-project-0.1.0.0"
libexecdir = "/home/raziel/.cabal/libexec"
sysconfdir = "/home/raziel/.cabal/etc"

getBinDir, getLibDir, getDataDir, getLibexecDir, getSysconfDir :: IO FilePath
getBinDir = catchIO (getEnv "new_project_bindir") (\_ -> return bindir)
getLibDir = catchIO (getEnv "new_project_libdir") (\_ -> return libdir)
getDataDir = catchIO (getEnv "new_project_datadir") (\_ -> return datadir)
getLibexecDir = catchIO (getEnv "new_project_libexecdir") (\_ -> return libexecdir)
getSysconfDir = catchIO (getEnv "new_project_sysconfdir") (\_ -> return sysconfdir)

getDataFileName :: FilePath -> IO FilePath
getDataFileName name = do
  dir <- getDataDir
  return (dir ++ "/" ++ name)
