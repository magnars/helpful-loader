# helpful-loader

A Clojure library to load resources with helpful error messages.

## Install

Add `[helpful-loader "0.1.1"]` to `:dependencies` in your `project.clj`.

## Usage

Load one form worth of edn content from a file on the classpath:

```clj
(ns example
  (:require [helpful-loader.edn :as edn-loader]))

(edn-loader/load-one "my-config.edn")
```

So, with `resources` on the class path, this would pick up
`resources/my-config.edn` and load the edn form contained within.

It works pretty much like this:

```clj
(clojure.edn/read-string (slurp (clojure.java.io/resource "my-config.edn")))
```

### So, how is this better than just doing it myself?

Three reasons:

- If the file doesn't exist, you'd normally see this:

  ```
  No implementation of method: :make-reader of protocol:
   #'clojure.java.io/IOFactory found for class: nil
  ```

  With helpful-loader you see:

  ```
  Unable to load my-config.edn, no such file on classpath.
  ```

- If the file is malformed, you'd normally see this:

  ```
  EOF while reading
  ```

  With helpful-loader you see:

  ```
  Error in my-config.edn: EOF while reading
  ```

- If the file has multiple forms, it would normally just be ignored
  by the reader. And you would be left scratching your head wondering
  why your changes didn't show up at all.

  With helpful-loader you see:

  ```
  /my-config.edn should contain only a single form, but had 2 forms.
  ```

## More stuff

```
(edn-loader/load-one-or-nil "my-config.edn")
```

This one will just return `nil` if the file doesn't exist, but will still brook
no nonsense for existing files, like above.

## Development

Run tests with

    lein expectations

Run tests automatically on changes with

    lein autoexpect

## License

Copyright © 2015 Magnar Sveen & Christian Johansen

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
