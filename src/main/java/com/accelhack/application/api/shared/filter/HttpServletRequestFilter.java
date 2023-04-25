package com.accelhack.application.api.shared.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;

public class HttpServletRequestFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    MultiReadHttpServletRequest multiReadRequest = new MultiReadHttpServletRequest((HttpServletRequest) servletRequest);
    filterChain.doFilter(multiReadRequest, servletResponse);
  }

  static class MultiReadHttpServletRequest extends HttpServletRequestWrapper {
    private ByteArrayOutputStream cachedBytes;

    public MultiReadHttpServletRequest(HttpServletRequest request) {
      super(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
      if (cachedBytes == null) cacheInputStream();
      return new CachedServletInputStream();
    }

    @Override
    public BufferedReader getReader() throws IOException {
      return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    private void cacheInputStream() throws IOException {
      // 複数回読めるようにInputStreamをキャッシュします。
      cachedBytes = new ByteArrayOutputStream();
      IOUtils.copy(super.getInputStream(), cachedBytes);
    }

    // キャッシュされたリクエストBodyを読むInputStream
    public class CachedServletInputStream extends ServletInputStream {
      private final ByteArrayInputStream input;

      public CachedServletInputStream() {
        // キャッシュされたリクエストBodyからInputStreamを作ります
        input = new ByteArrayInputStream(cachedBytes.toByteArray());
      }

      @Override
      public int read() throws IOException {
        return input.read();
      }

      @Override
      public boolean isFinished() {
        return input.available() == 0;
      }

      @Override
      public boolean isReady() {
        return true;
      }

      @Override
      public void setReadListener(ReadListener listener) {
        throw new RuntimeException("未実装です");
      }
    }
  }
}

